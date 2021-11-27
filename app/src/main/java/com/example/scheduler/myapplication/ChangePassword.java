package com.example.scheduler.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    EditText editTextPassword, editTextConfirmPassword;
    String password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Display the title (based from the user's Android versions)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#1d1d1d'>"+"Change Password"+"</font>", Html.FROM_HTML_MODE_LEGACY));
        else
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#1d1d1d'>"+"Change Password"+"</font>"));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);

        /* GET DATA REGARDING THE STATE OF THE AUTHENTICATION */
        // Initialize Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        Button changePasswordButton = findViewById(R.id.change_password_button);
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextPassword = findViewById(R.id.password_input);
                password = editTextPassword.getText().toString().trim();

                editTextConfirmPassword = findViewById(R.id.confirm_password);
                confirmPassword = editTextConfirmPassword.getText().toString().trim();
                // Allow or deny the change of password by checking
                // whether the password confirmation is equal to the password
                if (confirmPassword.equals(password)) {
                    currentUser.updatePassword(confirmPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ChangePassword.this, "Password changed successfully",
                                        Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(ChangePassword.this, "Something went wrong. Try again later",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(ChangePassword.this, "Passwords do not match",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}