package com.example.scheduler.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalTime;

public class EventEditActivity extends Fragment {
    View rootView;
    private EditText eventNameET;
    private TextView eventDateTV, eventTimeTV;

    private LocalTime time;

    public EventEditActivity() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_event_edit, container, false);

        initWidgets(rootView);
        time = LocalTime.now();
        eventDateTV.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
        eventTimeTV.setText("Time: " + CalendarUtils.formattedTime(time));

        Button saveEventAction = (Button) rootView.findViewById(R.id.save_event_action);
        saveEventAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName = eventNameET.getText().toString();
                Event newEvent = new Event(eventName, CalendarUtils.selectedDate, time);
                Event.eventsList.add(newEvent);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return rootView;
    }

    private void initWidgets(View rootView)
    {
        eventNameET = rootView.findViewById(R.id.eventNameET);
        eventDateTV = rootView.findViewById(R.id.eventDateTV);
        eventTimeTV = rootView.findViewById(R.id.eventTimeTV);
    }
}