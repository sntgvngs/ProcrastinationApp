package com.team1.cmsc434.procrastinationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Santiago Vanegas Marino on 11/8/2017.
 */

public class HomeActivity extends AppCompatActivity {
    private final String TAG = "HOME_ACTIVITY";

    private TaskAdapter mAdapter;

    ListView places;
    ImageButton viewAllButton;
    ImageButton addNewButton;

    @Override
    public void onCreate(Bundle savedInstanceStance) {
        super.onCreate(savedInstanceStance);

        setContentView(R.layout.home_layout);

        places = findViewById(R.id.home_task_list);

        viewAllButton = findViewById(R.id.view_all_button);
        addNewButton = findViewById(R.id.add_new_button);

        places.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Task task = (Task) mAdapter.getItem(pos);

                Intent intent = task.packageToIntent();

                intent.setClass(getApplicationContext(),ViewDetails.class);
                startActivity(intent);
            }
        });

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

        mAdapter = new TaskAdapter(getApplicationContext());

        places.setAdapter(mAdapter);
    }
}
