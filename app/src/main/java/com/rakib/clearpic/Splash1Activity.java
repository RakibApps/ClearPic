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
import android.widget.ScrollView;
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
import java.util.regex.*;
import org.json.*;

public class Splash1Activity extends AppCompatActivity {
	
	private ScrollView vscroll2;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear4;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout sp;
	private ScrollView vscroll1;
	private LinearLayout linear3;
	private TextView textview1;
	private TextView TextView1;
	private ImageView ImageView2;
	
	private Intent in = new Intent();
	private SharedPreferences splash;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.splash1);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		vscroll2 = findViewById(R.id.vscroll2);
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		linear4 = findViewById(R.id.linear4);
		linear6 = findViewById(R.id.linear6);
		linear7 = findViewById(R.id.linear7);
		sp = findViewById(R.id.sp);
		vscroll1 = findViewById(R.id.vscroll1);
		linear3 = findViewById(R.id.linear3);
		textview1 = findViewById(R.id.textview1);
		TextView1 = findViewById(R.id.TextView1);
		ImageView2 = findViewById(R.id.ImageView2);
		splash = getSharedPreferences("on", Activity.MODE_PRIVATE);
		
		sp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				splash.edit().putString("on", "onn").commit();
				in.setClass(getApplicationContext(), Main0Activity.class);
				startActivity(in);
			}
		});
	}
	
	private void initializeLogic() {
		_status_bar_color("#000000", "#000000");
		LinearLayout getStartedBtn = (LinearLayout) findViewById(R.id.sp);
		
		// অ্যানিমেশন লোড
		android.view.animation.Animation fadeSlide = android.view.animation.AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_slide_up);
		
		// দৃশ্যমান করে অ্যানিমেশন চালু
		getStartedBtn.setAlpha(1f);
		getStartedBtn.startAnimation(fadeSlide);
	}
	
	public void _status_bar_color(final String _colour1, final String _colour2) {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) { 
			Window w = this.getWindow(); w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			w.setStatusBarColor(Color.parseColor(_colour1)); w.setNavigationBarColor(Color.parseColor(_colour2));
		}
	}
	
}