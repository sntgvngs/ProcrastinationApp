package com.team1.cmsc434.procrastinationapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TabHost;
import android.widget.Toast;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

public class ViewAll extends AppCompatActivity {
    private final String TAG = "VIEW_ALL_ACTIVITY";
    TaskAdapter allAdapter;
    TaskAdapter dateAdapter;
    TaskAdapter importanceAdapter;

    ListView allView;
    ListView dateView;
    ListView importanceView;

    Calendar startDate;
    Calendar endDate;
    DatePickerDialog.OnDateSetListener startDateListener;
    DatePickerDialog.OnDateSetListener endDateListener;

    RatingBar minRating;
    EditText startDateText;
    EditText endDateText;

    Button addNewButton;

    ArrayList<Task> tasks;

    @Override
    public void onCreate(Bundle savedInstanceStance) {
        super.onCreate(savedInstanceStance);

        setContentView(R.layout.view_all_task);

        TabHost host = findViewById(R.id.tabHost);
        host.setup();
        allView = findViewById(R.id.All);
        dateView = findViewById(R.id.date_tasks);
        importanceView = findViewById(R.id.importance_tasks);

        minRating = findViewById(R.id.filter_rating);

        startDateText = findViewById(R.id.start_date);
        endDateText = findViewById(R.id.end_date);
        startDate = Calendar.getInstance();
        endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("All");
        spec.setContent(R.id.All);
        spec.setIndicator("All");
        host.addTab(spec);

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

        allAdapter = new TaskAdapter(getApplicationContext());
        dateAdapter = new TaskAdapter(getApplicationContext());
        importanceAdapter = new TaskAdapter(getApplicationContext());

        allView.setAdapter(allAdapter);
        dateView.setAdapter(dateAdapter);
        importanceView.setAdapter(importanceAdapter);

        allView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Log.d(TAG, "Tapped a list item.");
                Task task = (Task) allAdapter.getItem(pos);

                Intent intent = task.packageToIntent();

                intent.setClass(getApplicationContext(),ViewDetails.class);
                startActivity(intent);
            }
        });

        dateView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Log.d(TAG, "Tapped a list item.");
                Task task = (Task) dateAdapter.getItem(pos);

                Intent intent = task.packageToIntent();

                intent.setClass(getApplicationContext(),ViewDetails.class);
                startActivity(intent);
            }
        });

        importanceView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Log.d(TAG, "Tapped a list item.");
                Task task = (Task) importanceAdapter.getItem(pos);

                Intent intent = task.packageToIntent();

                intent.setClass(getApplicationContext(),ViewDetails.class);
                startActivity(intent);
            }
        });

        startDateListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                startDate.set(Calendar.YEAR, year);
                startDate.set(Calendar.MONTH, monthOfYear);
                startDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabels();
            }

        };

        endDateListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                endDate.set(Calendar.YEAR, year);
                endDate.set(Calendar.MONTH, monthOfYear);
                endDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabels();
            }

        };

        startDateText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(ViewAll.this, startDateListener, startDate
                        .get(Calendar.YEAR), startDate.get(Calendar.MONTH),
                        startDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        endDateText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(ViewAll.this, endDateListener, endDate
                        .get(Calendar.YEAR), endDate.get(Calendar.MONTH),
                        endDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        minRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                refreshAdapters();
            }
        });

        addNewButton = findViewById(R.id.add_new_button);

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

        tasks = new ArrayList<>();
        FileInputStream fis;
        try {
            Log.d(TAG, "Trying to read file.");
            fis = openFileInput(HomeActivity.dataFile);
            Scanner scanner = new Scanner(fis);
            scanner.useDelimiter("`"); // ` will separate entries in the file
            while(scanner.hasNext())
                tasks.add(new Task(scanner.next()));
            scanner.close();
            fis.close();
            Log.d(TAG, "File read successfully.");
        } catch (java.io.IOException e) {
            Log.d(TAG, "Unable to access dataFile. Has user added any tasks?");
        }

        Collections.sort(tasks);
        refreshAdapters();
    }

    private void refreshAdapters(){
        allAdapter.clear();
        dateAdapter.clear();
        importanceAdapter.clear();

        for(Task t:tasks){
            allAdapter.add(t);
            if(startDate.before(t.dueDate) && t.dueDate.before(endDate.getTime()))
                dateAdapter.add(t);
            if(t.importance >= minRating.getRating())
                importanceAdapter.add(t);
        }
    }

    private void updateLabels() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        startDateText.setText(sdf.format(startDate.getTime()));
        endDateText.setText(sdf.format(endDate.getTime()));
    }
}
