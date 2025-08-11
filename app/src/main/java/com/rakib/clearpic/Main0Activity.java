package com.rakib.clearpic;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.Window;
import android.view.WindowManager;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class Main0Activity extends AppCompatActivity {

private static final int REQ_PERM = 1001;  
private GridView gridView;  
private ArrayList<String> imagePaths = new ArrayList<>();  

@Override  
protected void onCreate(Bundle savedInstanceState) {  
    super.onCreate(savedInstanceState);  
    setContentView(R.layout.main0); // নিশ্চিত করো layout নাম main0.xml আছে  

    gridView = findViewById(R.id.gridViewPhotos);  
	_status_bar_color("#000000", "#000000");  
    // permission check  
    if (hasImagePermission()) {  
        loadRecentImages();  
    } else {  
        requestImagePermission();  
    }  

    // simple click: show toast or later implement full-screen viewer  
    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {  
        @Override  
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {  
            String path = imagePaths.get(position);  
            Toast.makeText(Main0Activity.this, "Image: " + path, Toast.LENGTH_SHORT).show();  
            // এখানে তোমরা Intent দিয়ে fullscreen viewer খুলতে পারো  
        }  
    });  
}  

private boolean hasImagePermission() {  
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {  
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED;  
    } else {  
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;  
    }  
}  

private void requestImagePermission() {  
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {  
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_MEDIA_IMAGES}, REQ_PERM);  
    } else {  
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQ_PERM);  
    }  
}  

@Override  
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {  
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);  
    if (requestCode == REQ_PERM) {  
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {  
            loadRecentImages();  
        } else {  
            Toast.makeText(this, "Permission denied. Can't load images.", Toast.LENGTH_SHORT).show();  
        }  
    }  
}  
public void _status_bar_color(final String _colour1, final String _colour2) {

	if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {   
		Window w = this.getWindow(); w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);  
		w.setStatusBarColor(Color.parseColor(_colour1)); w.setNavigationBarColor(Color.parseColor(_colour2));  
	}

}  
private void loadRecentImages() {  
    imagePaths.clear();  
    Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;  

    String[] projection;  
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {  
        // Note: DATA is deprecated on Q+, but many devices still provide it.  
        projection = new String[]{MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_ADDED};  
    } else {  
        projection = new String[]{MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_ADDED};  
    }  

    String orderBy = MediaStore.Images.Media.DATE_ADDED + " DESC";  

    Cursor cursor = getContentResolver().query(uri, projection, null, null, orderBy);  
    if (cursor != null) {  
        int dataColumn = -1;  
        try {  
            dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);  
        } catch (Exception e) {  
            // some devices may not expose DATA column; skip if missing  
            e.printStackTrace();  
        }  

        while (cursor.moveToNext()) {  
            if (dataColumn != -1) {  
                String path = cursor.getString(dataColumn);  
                if (path != null && path.length() > 0) {  
                    imagePaths.add(path);  
                }  
            }  
            if (imagePaths.size() >= 200) break; // limit  
        }  
        cursor.close();  
    }  

    ImageAdapter adapter = new ImageAdapter();  
    gridView.setAdapter(adapter);  
}  

// Inner adapter class  
private class ImageAdapter extends BaseAdapter {  

    @Override  
    public int getCount() {  
        return imagePaths.size();  
    }  

    @Override  
    public Object getItem(int position) {  
        return imagePaths.get(position);  
    }  

    @Override  
    public long getItemId(int position) {  
        return position;  
    }  

    @Override  
    public View getView(int position, View convertView, ViewGroup parent) {  
        final ImageView iv;  
        if (convertView == null) {  
            iv = new ImageView(Main0Activity.this);  
            int size = (int) (getResources().getDisplayMetrics().widthPixels / 3.0 - 12);  
            iv.setLayoutParams(new GridView.LayoutParams(size, size));  
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);  
        } else {  
            iv = (ImageView) convertView;  
        }  

        final String path = imagePaths.get(position);  

        // load thumbnail async  
        new AsyncTask<ImageView, Void, Bitmap>() {  
            private ImageView target;  

            @Override  
            protected Bitmap doInBackground(ImageView... params) {  
                target = params[0];  
                try {  
                    BitmapFactory.Options options = new BitmapFactory.Options();  
                    options.inJustDecodeBounds = true;  
                    BitmapFactory.decodeFile(path, options);  

                    int reqSize = 300;  
                    int height = options.outHeight;  
                    int width = options.outWidth;  
                    int inSample = 1;  
                    if (width > 0 && height > 0) {  
                        while ((width / inSample) > reqSize || (height / inSample) > reqSize) {  
                            inSample *= 2;  
                        }  
                    }  
                    options.inSampleSize = inSample;  
                    options.inJustDecodeBounds = false;  
                    return BitmapFactory.decodeFile(path, options);  
                } catch (Exception e) {  
                    e.printStackTrace();  
                    return null;  
                }  
            }  

            @Override  
            protected void onPostExecute(Bitmap bitmap) {  
                if (bitmap != null && target != null) {  
                    target.setImageBitmap(bitmap);  
                } else if (target != null) {  
                    target.setImageResource(android.R.drawable.ic_menu_report_image);  
                }  
            }  
        }.execute(iv);  

        return iv;  
    }  
}

}
