package com.team1.cmsc434.procrastinationapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

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
                Toast.makeText(getApplicationContext(), taskName.getText(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), String.valueOf(taskType.getSelectedItem()), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), taskDate.getText(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), String.valueOf(taskDifficulty.getSelectedItem()), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "" + taskImportance.getRating(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), taskDetails.getText(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
