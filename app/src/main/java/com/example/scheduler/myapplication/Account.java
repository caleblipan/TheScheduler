package com.example.scheduler.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Account extends Fragment {
    private View rootView;
    private BottomNavigationView bottomNavigationView;
    private FirebaseAuth mAuth;

    public Account() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_account, container, false);
        TextView noAccountButton = (TextView) rootView.findViewById(R.id.noAccount);

        // Bottom navigation menu
        bottomNavigationView = getActivity().getWindow().findViewById(R.id.bottomNavigationView);
        // Init firebase
        mAuth = FirebaseAuth.getInstance();
        getActivity().getWindow().findViewById(R.id.login_button).setVisibility(View.GONE);
        getActivity().getWindow().findViewById(R.id.account_button).setVisibility(View.VISIBLE);

        // Logout button onclick
        TextView logoutButton = (TextView) rootView.findViewById(R.id.logout_button);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Log out")
                        .setMessage("Are you sure you want to log out?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int which) {
                                FirebaseAuth.getInstance().signOut();

                                // Default selected bottom navigation menu
                                bottomNavigationView.setSelectedItemId(R.id.home_button);

                                // Go back home
                                Home home= new Home();
                                getFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_container, home)
                                        .commit();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .setIcon(R.drawable.logo_white)
                        .show();
            }
        });

        return rootView;
    }
}
