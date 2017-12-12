package com.team1.cmsc434.procrastinationapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

public class
EditTask extends AppCompatActivity {
    private final String TAG = "EDIT_TASK_ACTIVITY";

    EditText taskName;
    Spinner taskType;
    EditText taskDate;
    Spinner taskDifficulty;
    RatingBar taskImportance;
    EditText taskDetails;

    Button updateTaskButton;

    Calendar myCalendar;
    ArrayList<Task> others;

    DatePickerDialog.OnDateSetListener dateListener;

    @Override
    public void onCreate(Bundle savedInstanceStance) {
        super.onCreate(savedInstanceStance);

        myCalendar = Calendar.getInstance();

        setContentView(R.layout.edit_task_layout);

        taskName = findViewById(R.id.name_field);
        taskType = findViewById(R.id.type_field);
        taskDate = findViewById(R.id.date_field);
        taskDifficulty = findViewById(R.id.difficulty_field);
        taskImportance = findViewById(R.id.importance_field);
        taskDetails = findViewById(R.id.details_field);

        updateTaskButton = findViewById(R.id.update_task_button);

        Task original = new Task(getIntent());
        others = otherTasks(original);


        myCalendar.setTime(original.dueDate);

        taskName.setText(original.name);
        taskType.setSelection(original.type.ordinal());
        taskDifficulty.setSelection(original.difficulty.ordinal());
        taskImportance.setRating(original.importance);
        taskDetails.setText(original.details);

        updateLabel();

        taskDetails.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    updateTaskButton.performClick();
                    return true;
                }
                return false;
            }
        });

        dateListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        taskDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditTask.this, dateListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        updateTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(taskName.getText().toString() != "") {
                    others.add(new Task(taskName.getText().toString(),
                            Task.Type.valueOf(String.valueOf(taskType.getSelectedItem())),
                            myCalendar,
                            Task.Difficulty.valueOf(String.valueOf(taskDifficulty.getSelectedItem())),
                            taskImportance.getRating(), taskDetails.getText().toString()));

                    FileOutputStream fos;
                    try {
                        fos = openFileOutput(HomeActivity.dataFile, Context.MODE_PRIVATE);
                        for (Task t: others)
                            fos.write(t.packageForFile().getBytes());
                        fos.close();
                        Log.d(TAG, "Wrote to datafile.");
                    } catch (java.io.IOException e) {
                        Log.d(TAG, "Could not write to datafile.");
                    }
                    Toast.makeText(getApplicationContext(), "Task updated.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        taskDate.setText(sdf.format(myCalendar.getTime()));
    }

    private ArrayList<Task> otherTasks(Task orig) {
        ArrayList<Task> tasks = new ArrayList<>();
        FileInputStream fis;
        try {
            fis = openFileInput(HomeActivity.dataFile);
            Scanner scanner = new Scanner(fis);
            scanner.useDelimiter("`"); // ` will separate entries in the file
            while(scanner.hasNext()) {
                Task t = new Task(scanner.next());
                if(!t.equals(orig))
                    tasks.add(t);
            }
            scanner.close();
            fis.close();
        } catch (java.io.IOException e) {
            Log.d(TAG, "Unable to access dataFile. Has user added any tasks?");
        }

        return tasks;
    }
}