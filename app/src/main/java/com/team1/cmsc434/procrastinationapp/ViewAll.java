package com.team1.cmsc434.procrastinationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.io.FileInputStream;
import java.util.Scanner;

public class ViewAll extends AppCompatActivity {
    private final String TAG = "VIEW_ALL_ACTIVITY";
    CheckAdapter mAdapter;
    ListView lview;
    Button addNewButton;

    @Override
    public void onCreate(Bundle savedInstanceStance) {
        super.onCreate(savedInstanceStance);

        setContentView(R.layout.view_all_task);

        TabHost host = (TabHost) findViewById(R.id.tabHost);
        host.setup();
        lview = findViewById(R.id.Checklist);

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Checklist");
        spec.setContent(R.id.Checklist);
        spec.setIndicator("Checklist");
        host.addTab(spec);
        mAdapter = new CheckAdapter(getApplicationContext());
        lview.setAdapter(mAdapter);


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

    @Override
    public void onStart(){
        super.onStart();
        mAdapter.clear();
        FileInputStream fis;
        try {
            Log.d(TAG, "Trying to read file.");
            fis = openFileInput(HomeActivity.dataFile);
            Scanner scanner = new Scanner(fis);
            scanner.useDelimiter("`"); // ` will separate entries in the file
//            scanner.next(); // First entry is empty?
            Log.d(TAG, "Scanner had string: " + scanner.next());
            while(scanner.hasNext())
                mAdapter.add(new Task(scanner.next()));
            scanner.close();
            fis.close();
            Log.d(TAG, "File read successfully.");
        } catch (java.io.IOException e) {
            Log.d(TAG, "Unable to access dataFile. Has user added any tasks?");
        }
    }
}
