package com.team1.cmsc434.procrastinationapp;

import android.arch.persistence.room.*;

import java.util.Date;

@Entity(tableName = "tasks")
public class Task{
    public enum Type {Assignment, Event}
    public enum Difficulty {Easy, Medium, Hard}

    public Type type;
    public Difficulty difficulty;
    public String name;

    @PrimaryKey
    public Date dueDate;
    public float importance;
    public String details;

    public Boolean complete;

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
}
