package com.example.scheduler.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Settings extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        // Display the title (based from the user's Android versions)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            actionBar.setTitle(Html.fromHtml("<font color='#1d1d1d'>"+"Settings"+"</font>", Html.FROM_HTML_MODE_LEGACY));
        else
            actionBar.setTitle(Html.fromHtml("<font color='#1d1d1d'>"+"Settings"+"</font>"));

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);

        /* GET DATA REGARDING THE STATE OF THE AUTHENTICATION */
        // Initialize Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        // If the user has not logged in
        if (currentUser == null) {
            this.getWindow().findViewById(R.id.password_option).setVisibility(View.GONE);
            this.getWindow().findViewById(R.id.password_option_description).setVisibility(View.GONE);
            this.getWindow().findViewById(R.id.subscription_option).setVisibility(View.GONE);
            this.getWindow().findViewById(R.id.subscription_option_description).setVisibility(View.GONE);
            this.getWindow().findViewById(R.id.logout_button).setVisibility(View.GONE);
        }

        // Notifications option onclick
        TextView notificationsOption = findViewById(R.id.notifications_option);
        notificationsOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, NotificationsSettings.class);
                startActivity(intent);
            }
        });

        // Change password option onclick
        TextView changePasswordOption = findViewById(R.id.password_option);
        changePasswordOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
                    // Get the layout inflater
                    LayoutInflater inflater = getLayoutInflater();

                    // Inflate and set the layout for the dialog
                    // Pass null as the parent view because its going in the dialog layout
                    builder.setView(R.layout.dialog_signin)
                            // Add action buttons
                            .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    // sign in the user ...
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });
                    builder.show();
            }
        });

        // Logout button onclick
        TextView logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Settings.this)
                        .setTitle("Log out")
                        .setMessage("Are you sure you want to log out?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int which) {
                                FirebaseAuth.getInstance().signOut();

                                finish();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .setIcon(R.drawable.logo_white)
                        .show();
            }
        });
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