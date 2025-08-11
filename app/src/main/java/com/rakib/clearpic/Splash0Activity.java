package com.rakib.clearpic;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
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
import java.util.regex.*;
import org.json.*;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

public class Splash0Activity extends AppCompatActivity {
	
	private LinearLayout linear6;
	private TextView TextView4;
	private ImageView ImageView1;
	private TextView TextView2;
	private LinearLayout sp;
	private TextView TextView1;
	private ImageView ImageView2;
	
	private Intent in = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.splash0);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear6 = findViewById(R.id.linear6);
		TextView4 = findViewById(R.id.TextView4);
		ImageView1 = findViewById(R.id.ImageView1);
		TextView2 = findViewById(R.id.TextView2);
		sp = findViewById(R.id.sp);
		TextView1 = findViewById(R.id.TextView1);
		ImageView2 = findViewById(R.id.ImageView2);
		
		sp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in.setClass(getApplicationContext(), Splash1Activity.class);
				startActivity(in);
			}
		});
	}
	
	private void initializeLogic() {
		_status_bar_color("#D8D8D6", "#D8D8D6");
		ImageView topImage = findViewById(R.id.ImageView1);
		TextView title = findViewById(R.id.TextView2);
		LinearLayout getStartedBtn = findViewById(R.id.sp);
		TextView termsText = findViewById(R.id.TextView4);
		
		Animation fadeSlide = AnimationUtils.loadAnimation(this, R.anim.fade_in_slide_up);
		
		topImage.startAnimation(fadeSlide);
		title.startAnimation(fadeSlide);
		getStartedBtn.startAnimation(fadeSlide);
		termsText.startAnimation(fadeSlide);
		
		topImage.setAlpha(1f);
		title.setAlpha(1f);
		getStartedBtn.setAlpha(1f);
		termsText.setAlpha(1f);
		TextView textView = findViewById(R.id.TextView4);
		
		String fullText = "By continuing, you accept our Terms of Service and acknowledge receipt of our Privacy Policy.";
		SpannableString spannable = new SpannableString(fullText);
		
		final String url = "https://clearpic-nine.vercel.app/";
		
		// Terms of Service
		int termsStart = fullText.indexOf("Terms of Service");
		int termsEnd = termsStart + "Terms of Service".length();
		spannable.setSpan(new UnderlineSpan(), termsStart, termsEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		spannable.setSpan(new ClickableSpan() {
			@Override
			public void onClick(View widget) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				widget.getContext().startActivity(browserIntent);
			}
		}, termsStart, termsEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		// Privacy Policy
		int policyStart = fullText.indexOf("Privacy Policy");
		int policyEnd = policyStart + "Privacy Policy".length();
		spannable.setSpan(new UnderlineSpan(), policyStart, policyEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		spannable.setSpan(new ClickableSpan() {
			@Override
			public void onClick(View widget) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				widget.getContext().startActivity(browserIntent);
			}
		}, policyStart, policyEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		textView.setText(spannable);
		textView.setMovementMethod(LinkMovementMethod.getInstance());
		textView.setHighlightColor(Color.TRANSPARENT);
	}
	
	public void _status_bar_color(final String _colour1, final String _colour2) {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) { 
			Window w = this.getWindow(); w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			w.setStatusBarColor(Color.parseColor(_colour1)); w.setNavigationBarColor(Color.parseColor(_colour2));
		}
	}
	
}