package com.example.scheduler.myapplication;

import com.google.firebase.database.Exclude;

public class UserList {
    public String Title, Date, TaskType, Time;

    public UserList() {

    }

    /* GET ALL TODOLIST DATA */
    public UserList(String date, String taskType, String time, String title) {
        Date = date;
        TaskType = taskType;
        Time = time;
        Title = title;
    }

    // Getters
    @Exclude
    public String getTitle() {
        return Title;
    }

    @Exclude
    public void setTitle(String title) {
        Title = title;
    }

    @Exclude
    public String getDate() {
        return Date;
    }

    @Exclude
    public void setDate(String date) {
        Date = date;
    }

    @Exclude
    public String getTaskType() {
        return TaskType;
    }

    @Exclude
    public void setTaskType(String taskType) {
        TaskType = taskType;
    }

    @Exclude
    public String getTime() {
        return Time;
    }

    @Exclude
    public void setTime(String time) {
        Time = time;
    }
}
