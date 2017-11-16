package com.team1.cmsc434.procrastinationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by Santiago Vanegas Marino on 11/8/2017.
 */

public class HomeActivity extends AppCompatActivity {
    private final String TAG = "HOME_ACTIVITY";

    ImageButton viewAllButton;
    ImageButton addNewButton;

    @Override
    public void onCreate(Bundle savedInstanceStance) {
        super.onCreate(savedInstanceStance);

        setContentView(R.layout.home_layout);

        viewAllButton = (ImageButton) findViewById(R.id.view_all_button);
        addNewButton = (ImageButton) findViewById(R.id.add_new_button);

        viewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ViewAll.class));
            }
        });

        addNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NewTask.class));
            }
        });

    }
}
