package com.example.scheduler.myapplication;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ActivityHome extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private int loading_time = 5000;
    private FirebaseAuth firebaseAuth;
    Button button;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ActionBar
        // ActionBar actionBar = getSupportActionBar();
        // actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo_green_action_bar);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Bottom navigation menu
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            findViewById(R.id.login_button).setVisibility(View.GONE);
            findViewById(R.id.account_button).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.login_button).setVisibility(View.VISIBLE);
            findViewById(R.id.account_button).setVisibility(View.GONE);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        // Default selected bottom navigation menu
        bottomNavigationView.setSelectedItemId(R.id.home_button);
    }

    // Instantiate fragments for bottom navigation menu
    Home home = new Home();
    TodoList todoList = new TodoList();
    Schedule schedule = new Schedule();
    Login login = new Login();
    Account account = new Account();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_button:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, home).commit();
                return true;
            case R.id.list_button:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, todoList).commit();
                return true;
            case R.id.schedule_button:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, schedule).commit();
                return true;
            case R.id.login_button:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, login).commit();
                return true;
            case R.id.account_button:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, account).commit();
                return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    // Buttons for ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings_button:
                Intent intent = new Intent(ActivityHome.this, Settings.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}