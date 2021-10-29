package com.example.scheduler.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;import android.text.format.DateFormat;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        Bundle arguments1 = new Bundle();
        arguments1.putString("date", date1);
        TextView addTask1 = rootView.findViewById(R.id.add_task_1);
        addTask1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoList todoList= new TodoList();
                todoList.setArguments(arguments1);
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, todoList)
                        .commit();
            }
        });

        Bundle arguments2 = new Bundle();
        arguments2.putString("date", date2);
        TextView addTask2 = rootView.findViewById(R.id.add_task_2);
        addTask2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoList todoList= new TodoList();
                todoList.setArguments(arguments2);
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, todoList)
                        .commit();
            }
        });

        Bundle arguments3 = new Bundle();
        arguments3.putString("date", date3);
        TextView addTask3 = rootView.findViewById(R.id.add_task_3);
        addTask3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoList todoList= new TodoList();
                todoList.setArguments(arguments3);
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, todoList)
                        .commit();
            }
        });

        Bundle arguments4 = new Bundle();
        arguments4.putString("date", date4);
        TextView addTask4 = rootView.findViewById(R.id.add_task_4);
        addTask4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoList todoList= new TodoList();
                todoList.setArguments(arguments4);
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, todoList)
                        .commit();
            }
        });

        Bundle arguments5 = new Bundle();
        arguments5.putString("date", date5);
        TextView addTask5 = rootView.findViewById(R.id.add_task_5);
        addTask5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoList todoList= new TodoList();
                todoList.setArguments(arguments5);
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, todoList)
                        .commit();
            }
        });

        return rootView;
    }
}