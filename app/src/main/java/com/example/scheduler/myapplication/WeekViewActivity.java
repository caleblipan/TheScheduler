package com.example.scheduler.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.example.scheduler.myapplication.CalendarUtils.daysInMonthArray;
import static com.example.scheduler.myapplication.CalendarUtils.daysInWeekArray;
import static com.example.scheduler.myapplication.CalendarUtils.monthYearFromDate;

public class WeekViewActivity extends Fragment implements CalendarAdapter.OnItemListener {
    View rootView;
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ListView eventListView;

    public WeekViewActivity() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_week_view, container, false);

        initWidgets(rootView);
        setWeekView();

        Button previousWeekAction = (Button) rootView.findViewById(R.id.previous_week_action);
        previousWeekAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
                setWeekView();
            }
        });

        Button nextWeekAction = (Button) rootView.findViewById(R.id.next_week_action);
        nextWeekAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
                setWeekView();
            }
        });

        Button newEventAction = (Button) rootView.findViewById(R.id.new_event_action);
        newEventAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventEditActivity eventEditActivity = new EventEditActivity();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, eventEditActivity)
                        .commit();
            }
        });

        return rootView;
    }

    private void initWidgets(View rootView)
    {
        calendarRecyclerView = rootView.findViewById(R.id.calendarRecyclerView);
        monthYearText = rootView.findViewById(R.id.monthYearTV);
        eventListView = rootView.findViewById(R.id.eventListView);
    }

    private void setWeekView()
    {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        setEventAdpater();
    }

    @Override
    public void onItemClick(int position, LocalDate date)
    {
        CalendarUtils.selectedDate = date;
        setWeekView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        setEventAdpater();
    }

    private void setEventAdpater()
    {
        ArrayList<Event> dailyEvents = Event.eventsForDate(CalendarUtils.selectedDate);
        EventAdapter eventAdapter = new EventAdapter(getActivity(), dailyEvents);
        eventListView.setAdapter(eventAdapter);
    }
}