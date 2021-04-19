package com.example.todolist.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.todolist.R;
import com.example.todolist.model.Task;

import java.util.List;

/**
 * This class is the tasks adapter, it gets the view of a task.
 *
 * @author Christophe GARCIA, Sullivan LEBOEUF
 * @version 1.0
 */
public class AdapterTask extends BaseAdapter {

    /**
     * The tasks list.
     */
    private List<Task> listTask;

    /**
     * The context.
     */
    private Context context;

    /**
     * The inflater, used to create a new View (or Layout) object.
     */
    private LayoutInflater inflater;

    public AdapterTask(Context context, List<Task> list) {
        this.context = context;
        this.listTask = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView;

        // Create the view from the layout given if convertView is not null
        if (convertView == null) rootView = (View) inflater.inflate(R.layout.task_item, parent, false);
        else rootView = (View) convertView;

        // Set the task title, description, duration and checkbox
        TextView titleView = (TextView) rootView.findViewById(R.id.titleContextItem);
        titleView.setText(listTask.get(position).getTitle());
        TextView descriptionView = (TextView) rootView.findViewById(R.id.descriptionView);
        descriptionView.setText(listTask.get(position).getDescription());
        TextView durationView = (TextView) rootView.findViewById(R.id.durationView);
        durationView.setText(listTask.get(position).getDuration());
        ControllerCheckTask controllerCheckTask = new ControllerCheckTask(this.context, position);
        CheckBox checkBox = (CheckBox) rootView.findViewById(R.id.checkBoxTask);
        checkBox.setChecked(listTask.get(position).getIsChecked());
        checkBox.setOnClickListener(controllerCheckTask);

        // Set the task date according to the date choose
        String date = listTask.get(position).getDate();
        String[] parts = date.split("/");
        String day = parts[0];
        String month;
        switch (Integer.parseInt(parts[1])) {
            case 1:
                month = "Jan";
                break;
            case 2:
                month = "Fév";
                break;
            case 3:
                month = "Mar";
                break;
            case 4:
                month = "Avr";
                break;
            case 5:
                month = "Mai";
                break;
            case 6:
                month = "Jui";
                break;
            case 7:
                month = "Jll";
                break;
            case 8:
                month = "Aou";
                break;
            case 9:
                month = "Sep";
                break;
            case 10:
                month = "Oct";
                break;
            case 11:
                month = "Nov";
                break;
            case 12:
                month = "Déc";
                break;
            default:
                month = "";
        }
        TextView dateView = (TextView) rootView.findViewById(R.id.dateView);
        dateView.setText(day + " " + month);

        return rootView;
    }

    @Override
    public int getCount() {
        return this.listTask.size();
    }

    @Override
    public Object getItem(int position) {
        return listTask.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
