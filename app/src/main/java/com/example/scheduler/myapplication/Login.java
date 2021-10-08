package com.example.scheduler.myapplication;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Login extends Fragment {
    private View rootView;
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
        return rootView;
    }
}