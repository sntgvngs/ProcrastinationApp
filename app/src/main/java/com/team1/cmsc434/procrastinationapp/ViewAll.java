package com.team1.cmsc434.procrastinationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
        lview = findViewById(R.id.All);

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("All");
        spec.setContent(R.id.All);
        spec.setIndicator("All");
        host.addTab(spec);

        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Log.d(TAG, "Tapped a list item.");
                Task task = (Task) mAdapter.getItem(pos);

                Intent intent = task.packageToIntent();

                intent.setClass(getApplicationContext(),ViewDetails.class);
                startActivity(intent);
            }
        });

        mAdapter = new CheckAdapter(getApplicationContext());
        lview.setAdapter(mAdapter);


        //Tab 2
        spec = host.newTabSpec("Date");
        spec.setContent(R.id.Date);
        spec.setIndicator("Date");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Importance");
        spec.setContent(R.id.Importance);
        spec.setIndicator("Importance");
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
