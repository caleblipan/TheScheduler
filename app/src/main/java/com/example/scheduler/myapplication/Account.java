package com.example.scheduler.myapplication;

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

        bottomNavigationView.setSelectedItemId(R.id.account_button);

        return rootView;
    }
}
