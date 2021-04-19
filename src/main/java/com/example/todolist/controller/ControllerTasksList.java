package com.example.todolist.controller;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.todolist.CreateTaskActivity;
import com.example.todolist.MainActivity;
import com.example.todolist.model.Task;

/**
 * This class is a controller. This controller allows to see, modify and delete indirectly a task.
 *
 * @author Christophe GARCIA, Sullivan LEBOEUF
 * @version 1.0
 */
public class ControllerTasksList implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    /**
     * The context.
     *
     * @see MainActivity
     */
    private MainActivity mainActivity;

    /**
     * The constructor creates a new form of ControllerTasksList.
     *
     * @param ma the main activity
     */
    public ControllerTasksList(MainActivity ma) {
        if (ma != null) {
            this.mainActivity = ma;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Get the clicked task
        Task task = (Task) parent.getItemAtPosition(position);

        // Go to the create task activity and give the task values
        Intent modifyTask = new Intent(this.mainActivity, CreateTaskActivity.class);
        modifyTask.putExtra("title", task.getTitle());
        modifyTask.putExtra("description", task.getDescription());
        modifyTask.putExtra("context", task.getContext());
        modifyTask.putExtra("date", task.getDate());
        modifyTask.putExtra("duration", task.getDuration());
        modifyTask.putExtra("position", position);
        modifyTask.putExtra("url", task.getUrl());
        modifyTask.putExtra("isChecked", task.getIsChecked());
        this.mainActivity.startActivity(modifyTask);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        // Ask first confirmation to delete the task
        new AlertDialog.Builder(this.mainActivity)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Supprimer")
                .setMessage("Supprimer la tâche : " + Controller.getInstance(this.mainActivity).getTasksList().get(position).getTitle() + " ?")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Delete the task
                        Controller.getInstance(ControllerTasksList.this.mainActivity).deleteTask(position);
                        ControllerTasksList.this.mainActivity.updateTasksList();
                    }

                })
                .setNegativeButton("Non", null)
                .show();
        return true;
    }
}
