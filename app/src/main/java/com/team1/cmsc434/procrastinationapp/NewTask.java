package com.team1.cmsc434.procrastinationapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Santiago Vanegas Marino on 11/8/2017.
 */

public class NewTask extends AppCompatActivity {
    private final String TAG = "NEW_TASK_ACTIVITY";

    Button addTaskButton;

    @Override
    public void onCreate(Bundle savedInstanceStance) {
        super.onCreate(savedInstanceStance);

        setContentView(R.layout.add_new_task_layout);

        addTaskButton = (Button) findViewById(R.id.add_task_button);


        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "A new task would be added now", Toast.LENGTH_SHORT)
                        .show();
                finish();
            }
        });

    }
}
