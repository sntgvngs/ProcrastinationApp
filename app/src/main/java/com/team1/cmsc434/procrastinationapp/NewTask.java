package com.team1.cmsc434.procrastinationapp;

import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Santiago Vanegas Marino on 11/8/2017.
 */

public class NewTask extends AppCompatActivity {
    private final String TAG = "NEW_TASK_ACTIVITY";

    EditText taskName;
    Spinner taskType;
    EditText taskDate;
    Spinner taskDifficulty;
    RatingBar taskImportance;
    EditText taskDetails;

    Button addTaskButton;

    @Override
    public void onCreate(Bundle savedInstanceStance) {
        super.onCreate(savedInstanceStance);

        setContentView(R.layout.add_new_task_layout);

        taskName = findViewById(R.id.name_field);
        taskType = findViewById(R.id.type_field);
        taskDate = findViewById(R.id.date_field);
        taskDifficulty = findViewById(R.id.difficulty_field);
        taskImportance = findViewById(R.id.importance_field);
        taskDetails = findViewById(R.id.details_field);

        addTaskButton = findViewById(R.id.add_task_button);


        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Task adding = new Task(taskName.getText().toString(),
                        Task.Type.valueOf(String.valueOf(taskType.getSelectedItem())),
                        new Date(),
                        Task.Difficulty.valueOf(String.valueOf(taskDifficulty.getSelectedItem())),
                        taskImportance.getRating(), taskDetails.getText().toString());

                FileOutputStream fos;
                try {
                    fos = openFileOutput(HomeActivity.dataFile, Context.MODE_APPEND);
                    fos.write(adding.packageForFile().getBytes());
                    fos.close();
                    Log.d(TAG, "Wrote to datafile.");
                } catch (java.io.IOException e) {
                    Log.d(TAG, "Could not write to datafile.");
                }
                Toast.makeText(getApplicationContext(), "Task added.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
