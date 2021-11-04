package com.example.scheduler.myapplication;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Register extends Fragment {
    private View rootView;
    private FirebaseAuth mAuth;
    BottomNavigationView bottomNavigationView;
    EditText editTextEmail, editTextUsername, editTextPassword;
    String email, username, password;

    public Register() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_register, container, false);
        TextView haveAnAccountButton = (TextView) rootView.findViewById(R.id.haveAnAccount);
        haveAnAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Login login= new Login();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, login)
                        .commit();
            }
        });

        // Initialize Firebase Realtime Database
        DatabaseReference ref = FirebaseDatabase
                .getInstance("https://thescheduler-dfb0a-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference();

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Bottom navigation menu
        bottomNavigationView = rootView.findViewById(R.id.bottomNavigationView);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            rootView.findViewById(R.id.login_button).setVisibility(View.GONE);
            rootView.findViewById(R.id.account_button).setVisibility(View.VISIBLE);
        }

        Button registerButton = rootView.findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextEmail = (EditText) rootView.findViewById(R.id.EmailAddress);
                editTextUsername = (EditText) rootView.findViewById(R.id.Username);
                editTextPassword = (EditText) rootView.findViewById(R.id.Password);
                email = editTextEmail.getText().toString().trim();
                username = editTextUsername.getText().toString().trim();
                password = editTextPassword.getText().toString().trim();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Store values in Realtime Database
                                    HashMap<String, Object> mHashmap = new HashMap<>();

                                    mHashmap.put("credentials/" + email.replace(".", ",") + "/email", email);
                                    mHashmap.put("credentials/" + email.replace(".", ",") + "/password", password);

                                    ref.updateChildren(mHashmap);

                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Home home= new Home();
                                    getFragmentManager().beginTransaction()
                                            .replace(R.id.fragment_container, home)
                                            .commit();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(getActivity(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        return rootView;
    }
}