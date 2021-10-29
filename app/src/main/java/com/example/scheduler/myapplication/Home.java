package com.example.scheduler.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;import android.text.format.DateFormat;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Home extends Fragment {
    private View rootView;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Calendar now = Calendar.getInstance();

        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        String date1 = "" + now.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()) + ", " + now.get(Calendar.DATE) + " " + now.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        TextView firstDate = rootView.findViewById(R.id.date_1);
        firstDate.setText(date1);

        now.add(Calendar.DATE, 1);
        String date2 = "" + now.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()) + ", " + now.get(Calendar.DATE) + " " + now.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        TextView secondDate = rootView.findViewById(R.id.date_2);
        secondDate.setText(date2);

        now.add(Calendar.DATE, 1);
        String date3 = "" + now.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()) + ", " + now.get(Calendar.DATE) + " " + now.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        TextView thirdDate = rootView.findViewById(R.id.date_3);
        thirdDate.setText(date3);

        now.add(Calendar.DATE, 1);
        String date4 = "" + now.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()) + ", " + now.get(Calendar.DATE) + " " + now.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        TextView fourthDate = rootView.findViewById(R.id.date_4);
        fourthDate.setText(date4);

        now.add(Calendar.DATE, 1);
        String date5 = "" + now.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()) + ", " + now.get(Calendar.DATE) + " " + now.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        TextView fifthDate = rootView.findViewById(R.id.date_5);
        fifthDate.setText(date5);

        return rootView;
    }
}