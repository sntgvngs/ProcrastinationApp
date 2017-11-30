package com.team1.cmsc434.procrastinationapp;

import android.arch.persistence.room.*;
import android.content.Intent;

import java.util.Date;
import java.util.Scanner;

public class Task{
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

    public Task(Intent intent) {
        this.name = intent.getStringExtra("questionID");
        this.type = Type.valueOf(intent.getStringExtra("answerID"));
        this.dueDate = new Date(intent.getLongExtra("dueDate",0));
        this.difficulty = Difficulty.valueOf(intent.getStringExtra("difficulty"));
        this.importance = intent.getFloatExtra("importance",0);
        this.details = intent.getStringExtra("details");
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
}
