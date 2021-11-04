package com.example.scheduler.myapplication;

import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TodoList extends Fragment {
    Spinner dropdown;
    private DatabaseReference mDatabase;
    private String userEmail;
    private FirebaseAuth mAuth;
    BottomNavigationView bottomNavigationView;
    EditText editTextTitle, editTextTaskDateInput, editTextTaskTimeInput;
    String spinnerValue, todolistTitle, todolistDate, todolistTime;
    public String title, date, taskType, time;

    public TodoList() {
        // Required empty public constructor
    }

    /* GET ALL TODOLIST DATA */
    public TodoList(String Date, String TaskType, String Time, String Title) {
        title = Date;
        date = TaskType;
        time = Time;
        title = Title;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_addtodo_list, container, false);

        // Bottom navigation menu
        bottomNavigationView = rootView.findViewById(R.id.bottomNavigationView);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            getActivity().getWindow().findViewById(R.id.login_button).setVisibility(View.GONE);
            getActivity().getWindow().findViewById(R.id.account_button).setVisibility(View.VISIBLE);
        }
        // Select type of todolist
        dropdown = rootView.findViewById(R.id.type);
        initializeSpinner();

        // Automatically add date
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String date = bundle.getString("date");
            EditText taskDateInput = rootView.findViewById(R.id.task_date_input);
            taskDateInput.setText(date);
        }

        TextView taskTimeInput = rootView.findViewById(R.id.task_time_input);
        taskTimeInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), R.style.TimePicker, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        taskTimeInput.setText("" + hourOfDay + ":" + minute);
                    }
                }, hour, minute, false);

                timePickerDialog.show();
            }
        });

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://thescheduler-dfb0a-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference ref = mDatabase.getReference();

        Button saveTodolistButton = rootView.findViewById(R.id.save_todolist_button);
        saveTodolistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextTitle = (EditText) rootView.findViewById(R.id.task_title_input);
                editTextTaskDateInput = (EditText) rootView.findViewById(R.id.task_date_input);
                editTextTaskTimeInput = (EditText) rootView.findViewById(R.id.task_time_input);

                todolistTitle = editTextTitle.getText().toString().trim();
                todolistDate = editTextTaskDateInput.getText().toString().trim();
                todolistTime = editTextTaskTimeInput.getText().toString().trim();

                // Get current user email
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if (user != null) {
                    userEmail = user.getEmail().replace(".", ",");
                }
                // Push to todolist
                String key = ref.child("todolist").push().getKey(); // Use this key to users (down below)
                Map<String, Object> mHashmap = new HashMap<>();

                mHashmap.put("todolist/" + userEmail + "/" + key + "/Date", todolistDate);
                mHashmap.put("todolist/" + userEmail + "/" + key + "/TaskType", spinnerValue);
                mHashmap.put("todolist/" + userEmail + "/" + key + "/Time", todolistTime);
                mHashmap.put("todolist/" + userEmail + "/" + key + "/Title", todolistTitle);

                ref.updateChildren(mHashmap);

                // Push to users
                Map<String, Object> userHashmap = new HashMap<>();

                userHashmap.put("credentials/" + userEmail + "/todolists/" + todolistDate + "/" + key + "/time", todolistTime);

                ref.updateChildren(userHashmap);

                // Redirect to Home page
                Home home= new Home();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, home)
                        .commit();
            }
        });

        return rootView;
    }

    private void initializeSpinner() {
        final java.util.List<String> typeList = new ArrayList<String>();
        typeList.add("Homework");
        typeList.add("Exam");
        typeList.add("Meeting");

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, typeList);
        dropdown.setAdapter(dataAdapter1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getActivity(), "Selected: " + typeList.get(position), Toast.LENGTH_SHORT).show();
                spinnerValue = typeList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public void setTitle(String Title) {
        title = Title;
    }

    public String getDate() {
        return date;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getTime() {
        return time;
    }
}