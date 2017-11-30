package com.team1.cmsc434.procrastinationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.Toast;

public class ViewAll extends AppCompatActivity {
    private final String TAG = "VIEW_ALL_ACTIVITY";

    Button addNewButton;

    @Override
    public void onCreate(Bundle savedInstanceStance) {
        super.onCreate(savedInstanceStance);

        setContentView(R.layout.view_all_task);

        TabHost host = (TabHost) findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Checklist");
        spec.setContent(R.id.Checklist);
        spec.setIndicator("Checklist");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Calendar");
        spec.setContent(R.id.Calendar);
        spec.setIndicator("Calendar");
        host.addTab(spec);


        addNewButton = (Button) findViewById(R.id.add_new_button);

        addNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NewTask.class));
            }
        });

    }
}
