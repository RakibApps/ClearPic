package com.rakib.clearpic;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Bundle;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.color.MaterialColors;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class MainActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private LinearLayout linear4;
	private TextView TextView1;
	private ImageView ImageView1;
	
	private TimerTask timer;
	private Intent in = new Intent();
	private SharedPreferences splash;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear4 = findViewById(R.id.linear4);
		TextView1 = findViewById(R.id.TextView1);
		ImageView1 = findViewById(R.id.ImageView1);
		splash = getSharedPreferences("on", Activity.MODE_PRIVATE);
		
		linear4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in.setClass(getApplicationContext(), Splash0Activity.class);
				startActivity(in);
			}
		});
	}
	
	private void initializeLogic() {
		_status_bar_color("#000000", "#000000");
		ImageView logo = findViewById(R.id.ImageView1);
		TextView text = findViewById(R.id.TextView1);
		
		Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
		Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);
		
		// লোগো fade-in হবে
		logo.startAnimation(fadeIn);
		logo.setAlpha(1f);
		
		// টেক্সট fade-in + slide-up হবে, কিন্তু একটু দেরিতে
		slideUp.setStartOffset(500);
		text.startAnimation(slideUp);
		text.setAlpha(1f);
		if (splash.contains("onn")) {
			timer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							in.setClass(getApplicationContext(), Main0Activity.class);
							startActivity(in);
						}
					});
				}
			};
			_timer.schedule(timer, (int)(3000));
		} else {
			timer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							in.setClass(getApplicationContext(), Splash0Activity.class);
							startActivity(in);
						}
					});
				}
			};
			_timer.schedule(timer, (int)(3000));
		}
	}
	
	public void _status_bar_color(final String _colour1, final String _colour2) {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) { 
			Window w = this.getWindow(); w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			w.setStatusBarColor(Color.parseColor(_colour1)); w.setNavigationBarColor(Color.parseColor(_colour2));
		}
	}
	
}