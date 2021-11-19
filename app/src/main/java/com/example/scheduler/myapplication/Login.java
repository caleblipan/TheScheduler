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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends Fragment {
    private View rootView;
    private FirebaseAuth mAuth;
    private BottomNavigationView bottomNavigationView;
    EditText editTextEmail, editTextPassword;
    String email, password;
    public Login() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_login, container, false);
        TextView noAccountButton = (TextView) rootView.findViewById(R.id.noAccount);
        noAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Register register= new Register();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, register)
                        .commit();
            }
        });

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        Button registerButton = rootView.findViewById(R.id.login_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextEmail = (EditText) rootView.findViewById(R.id.EmailAddress);
                editTextPassword = (EditText) rootView.findViewById(R.id.Password);
                email = editTextEmail.getText().toString().trim();
                password = editTextPassword.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    // Bottom navigation menu
                                    bottomNavigationView = rootView.findViewById(R.id.bottomNavigationView);
                                    FirebaseUser currentUser = mAuth.getCurrentUser();
                                    if (currentUser != null) {
                                        getActivity().getWindow().findViewById(R.id.login_button).setVisibility(View.GONE);
                                        getActivity().getWindow().findViewById(R.id.account_button).setVisibility(View.VISIBLE);
                                    }

                                    // Default selected bottom navigation menu
                                    // bottomNavigationView.setSelectedItemId(R.id.home_button);

                                    Home home= new Home();
                                    getFragmentManager().beginTransaction()
                                            .replace(R.id.fragment_container, home)
                                            .commit();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
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