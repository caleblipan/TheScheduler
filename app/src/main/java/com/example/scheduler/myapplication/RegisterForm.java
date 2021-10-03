package com.example.scheduler.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class RegisterForm extends AppCompatActivity {
	Button button;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_form);

		getSupportActionBar().setTitle("Register");
	}
}

