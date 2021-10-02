package com.example.karl.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.widget.TextView;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.AdapterView;  
import android.widget.ArrayAdapter;  
import android.widget.Spinner;  
import android.widget.Toast; 

public class MainActivity extends AppCompatActivity {
	Button button;
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		       
		getSupportActionBar().setTitle("ONEHealth");
		addListenerOnButton();
		// moveToSignUp();

		// Next Button Sign In
		/*
		Button nextButtonSignIn = (Button)findViewById(R.id.nextButtonSignIn);
    		nextButtonSignIn.setEnabled(false);
		*/
		// Next Button Sign Up
		/*
		Button nextButtonSignUp = (Button)findViewById(R.id.nextButtonSignUp);
    		nextButtonSignUp.setEnabled(false);
		*/
	}

	public void addListenerOnButton() {
		final Context context = this;
		button = (Button) findViewById(R.id.RegisterButton);

		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    Intent intent = new Intent(context, RegisterForm.class);
                            startActivity(intent);
			}
		});
	}
}