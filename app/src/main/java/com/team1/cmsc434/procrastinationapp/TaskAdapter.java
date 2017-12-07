package com.team1.cmsc434.procrastinationapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TaskAdapter extends BaseAdapter {
    private final String TAG = "TASK_ADAPTER";
    private final long DAY_LENGTH = 86400000;
    private final long WEEK_LENGTH = 604800000;
    private ArrayList<Task> tasks;
    private Context mContext;

    public TaskAdapter(Context context) {
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

    static class ViewHolder {
        ImageView icon;
        TextView name;
        TextView type;
        TextView date;
        RatingBar importance;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View newView = convertView;
        ViewHolder holder;

        Task curr = tasks.get(position);

        if (convertView == null) {
            holder = new ViewHolder();
            newView = inflater.inflate(R.layout.task_badge_view, parent, false);
            holder.icon = newView.findViewById(R.id.task_icon);
            holder.name = newView.findViewById(R.id.task_name);
            holder.type = newView.findViewById(R.id.task_type);
            holder.date = newView.findViewById(R.id.task_date);
            holder.importance = newView.findViewById(R.id.importance_rating);
            newView.setTag(holder);
        } else {
            holder = (ViewHolder) newView.getTag();
        }

        Date now = new Date();
        if(curr.type == Task.Type.Assignment) {
            holder.type.setText(Task.Type.Assignment.name());
            if(curr.dueDate.getTime() - now.getTime() < DAY_LENGTH) {
                if(curr.difficulty == Task.Difficulty.Easy)
                    holder.icon.setImageResource(R.drawable.icon_task_3_1);
                else if(curr.difficulty == Task.Difficulty.Medium)
                    holder.icon.setImageResource(R.drawable.icon_task_3_2);
                else// curr.difficulty == Task.Difficulty.Hard
                    holder.icon.setImageResource(R.drawable.icon_task_3_3);
            } else if(curr.dueDate.getTime() - now.getTime() <  WEEK_LENGTH) {
                if(curr.difficulty == Task.Difficulty.Easy)
                    holder.icon.setImageResource(R.drawable.icon_task_2_1);
                else if(curr.difficulty == Task.Difficulty.Medium)
                    holder.icon.setImageResource(R.drawable.icon_task_2_2);
                else// curr.difficulty == Task.Difficulty.Hard
                    holder.icon.setImageResource(R.drawable.icon_task_2_3);
            } else {
                if(curr.difficulty == Task.Difficulty.Easy)
                    holder.icon.setImageResource(R.drawable.icon_task_1_1);
                else if(curr.difficulty == Task.Difficulty.Medium)
                    holder.icon.setImageResource(R.drawable.icon_task_1_2);
                else// curr.difficulty == Task.Difficulty.Hard
                    holder.icon.setImageResource(R.drawable.icon_task_1_3);
            }
        } else {
            holder.type.setText(Task.Type.Event.name());
            if(curr.dueDate.getTime() - now.getTime() < DAY_LENGTH) {
                if(curr.difficulty == Task.Difficulty.Easy)
                    holder.icon.setImageResource(R.drawable.icon_event_3_1);
                else if(curr.difficulty == Task.Difficulty.Medium)
                    holder.icon.setImageResource(R.drawable.icon_event_3_2);
                else// curr.difficulty == Task.Difficulty.Hard
                    holder.icon.setImageResource(R.drawable.icon_event_3_3);
            } else if(curr.dueDate.getTime() - now.getTime() <  WEEK_LENGTH) {
                if(curr.difficulty == Task.Difficulty.Easy)
                    holder.icon.setImageResource(R.drawable.icon_event_2_1);
                else if(curr.difficulty == Task.Difficulty.Medium)
                    holder.icon.setImageResource(R.drawable.icon_event_2_2);
                else// curr.difficulty == Task.Difficulty.Hard
                    holder.icon.setImageResource(R.drawable.icon_event_2_3);
            } else {
                if(curr.difficulty == Task.Difficulty.Easy)
                    holder.icon.setImageResource(R.drawable.icon_event_1_1);
                else if(curr.difficulty == Task.Difficulty.Medium)
                    holder.icon.setImageResource(R.drawable.icon_event_1_2);
                else// curr.difficulty == Task.Difficulty.Hard
                    holder.icon.setImageResource(R.drawable.icon_event_1_3);
            }
        }
        holder.importance.setRating(curr.importance);
        holder.date.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(curr.dueDate));
        holder.name.setText("" + curr.name);

        return newView;
    }

    public void add(Task task){
        Log.d(TAG, "Adding new task: " + task.name);
        tasks.add(task);
        notifyDataSetChanged();
    }

    public void clear(){
        Log.d(TAG, "Deleting all tasks.");
        tasks = new ArrayList<Task>();
    }
}
