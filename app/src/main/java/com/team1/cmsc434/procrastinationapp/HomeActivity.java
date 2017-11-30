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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HomeActivity extends AppCompatActivity {
    private final String TAG = "HOME_ACTIVITY";
    public static final String dataFile = "taskData.txt";

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

        FileInputStream fis;
        try {
            Log.d(TAG, "Trying to read file.");
            fis = openFileInput(dataFile);
            Scanner scanner = new Scanner(fis);
            scanner.useDelimiter("\\u1337"); // ` will separate entries in the file
            scanner.next(); // First entry is empty?
            while(scanner.hasNext())
                mAdapter.add(new Task(scanner.next()));
            scanner.close();
            fis.close();
            Log.d(TAG, "File read successfully.");
        } catch (java.io.IOException e) {
            Log.d(TAG, "Unable to access dataFile. Has user added any tasks?");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        FileInputStream fis;
        try {
            Log.d(TAG, "Trying to read file.");
            fis = openFileInput(dataFile);
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
