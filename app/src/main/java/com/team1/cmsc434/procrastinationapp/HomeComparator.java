package com.team1.cmsc434.procrastinationapp;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class HomeComparator implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        float t1_time_left = t1.dueDate.getTime() / Calendar.getInstance().getTimeInMillis();
        float t2_time_left = t2.dueDate.getTime() / Calendar.getInstance().getTimeInMillis();
        return Math.round(t2.importance/t2_time_left - t1.importance /t1_time_left);
    }
}
