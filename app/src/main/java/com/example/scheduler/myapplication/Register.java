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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends Fragment {
    private View rootView;
    private FirebaseAuth mAuth;
    EditText editTextEmail, editTextPassword;
    String email, password;

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

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        Button registerButton = rootView.findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextEmail = (EditText) rootView.findViewById(R.id.EmailAddress);
                editTextPassword = (EditText) rootView.findViewById(R.id.Password);
                email = editTextEmail.getText().toString().trim();
                password = editTextPassword.getText().toString().trim();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
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