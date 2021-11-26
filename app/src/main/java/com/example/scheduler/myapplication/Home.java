package com.example.scheduler.myapplication;

import static android.content.ContentValues.TAG;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.renderscript.Sampler;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;import android.text.format.DateFormat;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Home extends Fragment {
    private View rootView;
    private FirebaseAuth firebaseAuth;
    private String userEmail;
    BottomNavigationView bottomNavigationView;
    List<String> titles = new ArrayList<String>();

    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        /* RENDER THE NEXT FIVE DAYS OF TODOLIST */
        renderNextFiveDays(rootView);

        /* GET DATA REGARDING THE STATE OF THE AUTHENTICATION */
        // Initialize Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        // Set visibility based on the state of the app
        if (currentUser != null) {
            getActivity().getWindow().findViewById(R.id.login_button).setVisibility(View.GONE);
            getActivity().getWindow().findViewById(R.id.account_button).setVisibility(View.VISIBLE);

            /* RENDER ALL AVAILABLE TODOLIST DATA */
            renderAllAvailableTodolistData(currentUser, rootView);
        }

        return rootView;
    }

    public void renderNextFiveDays(View rootView) {
        Calendar now = Calendar.getInstance();

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
        TextView addTaskRendered1 = rootView.findViewById(R.id.add_task_rendered_1);
        TextView[] addTaskArr = {addTask1, addTaskRendered1};

        for (int i = 0; i < 2; i++) {
            addTaskArr[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TodoList todoList = new TodoList();
                    todoList.setArguments(arguments1);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, todoList)
                            .commit();
                }
            });
        }

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
    }

    public void renderAllAvailableTodolistData(FirebaseUser currentUser, View rootView) {
        // Make it visible
        if (rootView.findViewById(R.id.show_todolist_text).getVisibility() == View.VISIBLE) {
            rootView.findViewById(R.id.show_todolist_text).setVisibility(View.GONE);
            rootView.findViewById(R.id.add_task_1).setVisibility(View.GONE);

            rootView.findViewById(R.id.render_text_1).setVisibility(View.VISIBLE);
            rootView.findViewById(R.id.add_task_rendered_1).setVisibility(View.VISIBLE);
        }

        // Initialize Firebase Realtime Database
        DatabaseReference ref = FirebaseDatabase
                .getInstance("https://thescheduler-dfb0a-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference();

        userEmail = currentUser.getEmail().replace(".", ",");

        Query todolistQuery = ref.child("todolist").child(userEmail);
        todolistQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> titles = new ArrayList<String>();
                List<String> times = new ArrayList<>();
                List<String> taskTypes = new ArrayList<>();
                List<String> date = new ArrayList<>();

                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    UserList userList = postSnapshot.getValue(UserList.class);
                    titles.add(userList.getTitle());
                    times.add(userList.getTime());
                    taskTypes.add(userList.getTaskType());
                    date.add(userList.getDate());
                }

                // Loop through the entire thing
                for (int i = 0; i < titles.size(); i++) {
                    SpannableStringBuilder str = new SpannableStringBuilder(" \t" + titles.get(i));
                    str.append(" ");
                    if (taskTypes.get(i).equals("Homework"))
                        str.setSpan(new ImageSpan(getActivity(), R.drawable.assignment_icon), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    else if (taskTypes.get(i).equals("Exam"))
                        str.setSpan(new ImageSpan(getActivity(), R.drawable.exam_icon), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    else if (taskTypes.get(i).equals("Meeting"))
                        str.setSpan(new ImageSpan(getActivity(), R.drawable.meeting_icon), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                    str.append("\t\t\t\t");
                    str.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                    if (taskTypes.get(i).equals("Homework"))
                        str.append("\n\t\t\t\tDue: ");
                    else
                        str.append("\n\t\t\t\tStart time: ");
                    str.append(times.get(i));
                    str.append("\n\n");

                    String stringDate = date.get(i);
                    stringDate = stringDate.replaceAll("[^0-9]", "").trim();

                    LocalDateTime today = LocalDateTime.now( ZoneId.of( "Asia/Jakarta" ) ) ;
                    String todaysDate = Integer.toString(today.getDayOfMonth()).trim();

                    TextView renderText;
                    // Check if it is equal to today or tomorrow or the day after tomorrow etc
                    if (stringDate.equals(todaysDate)) {
                        renderText = rootView.findViewById(R.id.render_text_1);
                        renderText.append(str);
                    } else if (stringDate.equals(Integer.toString(Integer.parseInt(todaysDate) + 1))) {
                        rootView.findViewById(R.id.show_todolist_text_2).setVisibility(View.GONE);
                        rootView.findViewById(R.id.add_task_2).setVisibility(View.GONE);

                        rootView.findViewById(R.id.render_text_2).setVisibility(View.VISIBLE);
                        rootView.findViewById(R.id.add_task_rendered_2).setVisibility(View.VISIBLE);
                        renderText = rootView.findViewById(R.id.render_text_2);
                        renderText.append(str);
                    } else if (stringDate.equals(Integer.toString(Integer.parseInt(todaysDate) + 2))) {
                        rootView.findViewById(R.id.show_todolist_text_3).setVisibility(View.GONE);
                        rootView.findViewById(R.id.add_task_3).setVisibility(View.GONE);

                        rootView.findViewById(R.id.render_text_3).setVisibility(View.VISIBLE);
                        rootView.findViewById(R.id.add_task_rendered_3).setVisibility(View.VISIBLE);
                        renderText = rootView.findViewById(R.id.render_text_3);
                        renderText.append(str);
                    } else if (stringDate.equals(Integer.toString(Integer.parseInt(todaysDate) + 3))) {
                        rootView.findViewById(R.id.show_todolist_text_4).setVisibility(View.GONE);
                        rootView.findViewById(R.id.add_task_4).setVisibility(View.GONE);

                        rootView.findViewById(R.id.render_text_4).setVisibility(View.VISIBLE);
                        rootView.findViewById(R.id.add_task_rendered_4).setVisibility(View.VISIBLE);
                        renderText = rootView.findViewById(R.id.render_text_4);
                        renderText.append(str);
                    } else if (stringDate.equals(Integer.toString(Integer.parseInt(todaysDate) + 4))) {
                        rootView.findViewById(R.id.show_todolist_text_5).setVisibility(View.GONE);
                        rootView.findViewById(R.id.add_task_5).setVisibility(View.GONE);

                        rootView.findViewById(R.id.render_text_5).setVisibility(View.VISIBLE);
                        rootView.findViewById(R.id.add_task_rendered_5).setVisibility(View.VISIBLE);
                        renderText = rootView.findViewById(R.id.render_text_5);
                        renderText.append(str);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}