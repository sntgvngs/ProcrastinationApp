package com.team1.cmsc434.procrastinationapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class CheckAdapter extends BaseAdapter {
    private final String TAG = "CHECK_ADAPTER";
    private ArrayList<Task> tasks;
    private Context mContext;

    public CheckAdapter(Context context) {
        mContext = context;
        tasks = new ArrayList<Task>();
        tasks.add(new Task());
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View newView = inflater.inflate(R.layout.check_badge_view, parent, false);

        Task curr = tasks.get(position);

        TextView name = newView.findViewById(R.id.check_name);
        CheckBox box = newView.findViewById(R.id.task_check);
        name.setText(curr.name);
        box.setChecked(curr.complete);

        return newView;
    }

    public void add(Task task){
        Log.d(TAG, "Adding new check: " + task.name);
        tasks.add(task);
        notifyDataSetChanged();
    }

    public void clear(){
        Log.d(TAG, "Deleting all checks.");
        tasks = new ArrayList<Task>();
    }

//    public void delete(String uid) {
//        for(int i = 0; i < tasks.size(); i++) {
//            if (tasks.get(i).id.equals(uid)) {
//                advisers.remove(i);
//                notifyDataSetChanged();
//                return;
//            }
//        }
//    }
}
