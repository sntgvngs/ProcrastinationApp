package com.team1.cmsc434.procrastinationapp;

import android.arch.persistence.room.*;

import java.util.List;

@Dao
public interface TaskDAO {
    @Query("SELECT * FROM tasks")
    List<Task> getAll();

    @Query("SELECT * FROM tasks WHERE type LIKE :type")
    Task findByType(Task.Type type);

    @Insert
    void insertAll(Task... users);

    @Delete
    void delete(Task user);
}
