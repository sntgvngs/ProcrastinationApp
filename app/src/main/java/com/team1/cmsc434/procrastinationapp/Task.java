package com.team1.cmsc434.procrastinationapp;

import android.arch.persistence.room.*;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Task implements Comparable<Task> {
    private final String TAG = "TASK_CLASS";

    public enum Type {Assignment, Event}
    public enum Difficulty {Easy, Medium, Hard}

    public Type type;
    public Difficulty difficulty;
    public String name;

    public Date dueDate;
    public float importance;
    public String details;

    public Boolean complete;

    public Task() {
        this.name = "test";
        this.type = Type.Assignment;
        this.dueDate = new Date();
        this.difficulty = Difficulty.Easy;
        this.importance = 2.5f;
        this.details = "some details?";
        this.complete = false;
    }

    public Task(String name, Type type, Date dueDate, Difficulty difficulty, float importance,
                String details) {
        this.name = name;
        this.type = type;
        this.dueDate = dueDate;
        this.difficulty = difficulty;
        this.importance = importance;
        this.details = details;
        this.complete = false;
    }

    public Task(String name, Type type, Calendar dueDate, Difficulty difficulty, float importance,
                String details) {
        this.name = name;
        this.type = type;
        this.dueDate = dueDate.getTime();
        this.difficulty = difficulty;
        this.importance = importance;
        this.details = details;
        this.complete = false;
    }

    public Task(Intent intent) {
        Log.d(TAG, "getting name...");
        this.name = intent.getStringExtra("name");
        Log.d(TAG, "getting type...");
        this.type = Type.valueOf(intent.getStringExtra("type"));
        Log.d(TAG, "getting dueDate...");
        this.dueDate = new Date(intent.getLongExtra("dueDate",0));
        Log.d(TAG, "getting difficulty...");
        this.difficulty = Difficulty.valueOf(intent.getStringExtra("difficulty"));
        Log.d(TAG, "getting importance...");
        this.importance = intent.getFloatExtra("importance",0);
        Log.d(TAG, "getting details...");
        this.details = intent.getStringExtra("details");
        Log.d(TAG, "getting complete...");
        this.complete = intent.getBooleanExtra("complete", false);
    }

    public Task(String ans) {
        Scanner scan = new Scanner(ans);
        scan.useDelimiter("~");
        this.name = scan.next();
        this.type = Type.valueOf(scan.next());
        this.dueDate = new Date(Long.parseLong(scan.next()));
        this.difficulty = Difficulty.valueOf(scan.next());
        this.importance = Float.parseFloat(scan.next());
        this.details = scan.next();
        this.complete = Boolean.parseBoolean(scan.next());
    }

    public Intent packageToIntent(){
        Intent intent = new Intent();
        intent.putExtra("name", name);
        intent.putExtra("type", type.name());
        intent.putExtra("dueDate", dueDate.getTime());
        intent.putExtra("difficulty", difficulty.name());
        intent.putExtra("importance", importance);
        intent.putExtra("details", details);
        intent.putExtra("complete", complete);
        return intent;
    }

    public String packageForFile(){
        String ans = "`" + name;
        ans += "~" + type.name();
        ans += "~" + dueDate.getTime();
        ans += "~" + difficulty.name();
        ans += "~" + importance;
        ans += "~" + details;
        ans += "~" + complete;
        return ans;
    }

    public boolean equals(Object o){
        Task other = (Task) o;
        if(this.name.equals(other.name))
            if(this.type.equals(other.type))
                if(this.dueDate.equals(other.dueDate))
                    if(this.difficulty.equals(other.difficulty))
                        if(this.importance == other.importance)
                            if(this.details.equals(other.details))
                                if(this.complete.equals(other.complete))
                                    return true;
        return false;
    }

    @Override public int compareTo(Task other){
        return this.dueDate.compareTo(other.dueDate);
    }
}
