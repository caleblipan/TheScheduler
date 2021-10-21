package com.example.scheduler.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static com.example.scheduler.myapplication.CalendarUtils.daysInMonthArray;
import static com.example.scheduler.myapplication.CalendarUtils.monthYearFromDate;

public class MainActivity extends AppCompatActivity  {

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