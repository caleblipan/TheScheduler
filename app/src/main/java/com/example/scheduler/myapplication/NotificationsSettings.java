package com.example.scheduler.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class NotificationsSettings extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_settings);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        // Display the title (based from the user's Android versions)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            actionBar.setTitle(Html.fromHtml("<font color='#1d1d1d'>"+"Notifications"+"</font>", Html.FROM_HTML_MODE_LEGACY));
        else
            actionBar.setTitle(Html.fromHtml("<font color='#1d1d1d'>"+"Notifications"+"</font>"));
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);


        String[] time = {"1 hour", "2 hours", "3 hours", "4 hours", "5 hours"};
        Spinner spinnerTasks = findViewById(R.id.notification_tasks_spinner);
        spinnerTasks.setOnItemSelectedListener(this);
        Spinner spinnerAppointments = findViewById(R.id.notification_appointments_spinner);
        spinnerAppointments.setOnItemSelectedListener(this);

        // Create the instance of ArrayAdapter
        // having the list of courses
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, time);

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spinnerTasks.setAdapter(ad);
        spinnerAppointments.setAdapter(ad);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View arg1, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Auto-generated method stub
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}