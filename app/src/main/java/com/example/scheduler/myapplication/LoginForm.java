package com.example.scheduler.myapplication;

import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.view.View;

public class LoginForm extends Activity {
	Button button;
	TextView textView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_form);
		moveToRegisterForm();
	}

	public void moveToRegisterForm() {
		final Context context = this;
		textView = (TextView) findViewById(R.id.NoAccount);

		textView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    Intent intent = new Intent(context, RegisterForm.class);
                            startActivity(intent);
			}
		});
	}

}

