package com.example.todolist.controller;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;

import com.example.todolist.MainActivity;

/**
 * This class is a controller. This controller allows to updates the state of the task check box.
 *
 * @author Christophe GARCIA, Sullivan LEBOEUF
 * @version 1.0
 */
public class ControllerCheckTask implements View.OnClickListener {

    /**
     * The context.
     */
    private Context context;

    /**
     * The position of the task.
     */
    private int position;

    /**
     * The constructor creates a new form of ControllerCheckTask.
     *
     * @param context the context
     * @param position the task position
     */
    public ControllerCheckTask(Context context, int position) {
        this.context = context;
        this.position = position;
    }

    @Override
    public void onClick(View v) {

        // If the user checks the check box
        if (((CheckBox) v).isChecked()) {
            Controller.getInstance(this.context).updateCheckTask(this.position, true);
        }

        // If the user unchecks the check box.
        else {
            Controller.getInstance(this.context).updateCheckTask(this.position, false);
        }

        // Updates the tasks list view
        ((MainActivity) this.context).updateTasksList();
    }
}
