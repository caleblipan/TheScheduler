package com.example.scheduler.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Handler;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

	private int loading_time = 2000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		getSupportActionBar().hide();

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent home=new Intent(MainActivity.this, ActivityHome.class);
				startActivity(home);
				finish();
			}
		},loading_time);

	}
}