package com.example.scheduler.myapplication;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Register extends Fragment {
    private View rootView;
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
        return rootView;
    }
}