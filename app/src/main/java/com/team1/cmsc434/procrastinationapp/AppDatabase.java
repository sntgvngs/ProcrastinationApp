package com.team1.cmsc434.procrastinationapp;

import android.arch.persistence.room.*;


@Database(entities = {Task.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDAO userDao();
}
