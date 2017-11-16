package com.team1.cmsc434.procrastinationapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by Santiago Vanegas Marino on 11/8/2017.
 */

public class ViewDetails extends AppCompatActivity {
    private final String TAG = "VIEW_DETAILS_ACTIVITY";

    @Override
    public void onCreate(Bundle savedInstanceStance) {
        super.onCreate(savedInstanceStance);

        setContentView(R.layout.view_task_details);

    }
}
