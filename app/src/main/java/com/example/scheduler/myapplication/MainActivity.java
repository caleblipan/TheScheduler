package com.example.scheduler.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {
	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle("The Scheduler");
		actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg));

		moveToLoginForm();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.main_activity_menu, menu);
		return true;
	}

	public void moveToLoginForm() {
		final Context context = this;
		button = (Button) findViewById(R.id.LoginButton);

		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    Intent intent = new Intent(context, LoginForm.class);
			    startActivity(intent);
			}
		});
	}
}