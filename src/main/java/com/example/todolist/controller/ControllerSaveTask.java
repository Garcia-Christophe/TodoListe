package com.example.todolist.controller;

import android.content.Intent;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolist.CreateTaskActivity;
import com.example.todolist.MainActivity;
import com.example.todolist.R;
import com.example.todolist.model.Task;

/**
 * This class is a controller. This controller allows to create indirectly a task.
 *
 * @author Christophe GARCIA, Sullivan LEBOEUF
 * @version 1.0
 */
public class ControllerSaveTask implements View.OnClickListener {

    /**
     * The context.
     *
     * @see CreateTaskActivity
     */
    private CreateTaskActivity createTaskActivity;

    /**
     * True if the user is creating a task, false if he is modifying one of them.
     */
    private boolean isCreating;

    /**
     * The modified task position.
     */
    private int positionModifiedTask;

    /**
     * The constructor creates a new form of ControllerSaveTask.
     *
     * @param ct the create task activity
     * @param isCreating the user action state
     * @param positionModifiedTask the modified task position
     */
    public ControllerSaveTask(CreateTaskActivity ct, boolean isCreating, int positionModifiedTask) {
        this.createTaskActivity = ct;
        this.isCreating = isCreating;
        this.positionModifiedTask = positionModifiedTask;
    }

    @Override
    public void onClick(View v) {

        // Retrieve the view components
        TextView titleInput = (TextView) this.createTaskActivity.findViewById(R.id.titleInput);
        TextView descriptionInput = (TextView) this.createTaskActivity.findViewById(R.id.descriptionInput);
        Spinner contextInput = (Spinner) this.createTaskActivity.findViewById(R.id.contextInput);
        NumberPicker hourPicker = (NumberPicker) this.createTaskActivity.findViewById(R.id.hourPicker);
        NumberPicker minPicker = (NumberPicker) this.createTaskActivity.findViewById(R.id.minPicker);
        TextView dateInput = (TextView) this.createTaskActivity.findViewById(R.id.dateInput);
        TextView urlInput = (TextView) this.createTaskActivity.findViewById(R.id.inputURL);

        // Check whether the title and the date have been entered
        if (titleInput.getText().toString() != null && titleInput.getText().toString().length() > 0 && dateInput.getText().toString() != null && !dateInput.getText().toString().equalsIgnoreCase("DD/MM/YYYY")) {
            if(descriptionInput.getText().toString() == null) descriptionInput.setText("");
            if(contextInput.getSelectedItem().toString() == null) contextInput.setSelection(0);

            // If the user is creating a task, then create a task in the database and the ArrayList
            if (this.isCreating) {
                Task task = new Task(0, titleInput.getText().toString(), descriptionInput.getText().toString(),
                        contextInput.getSelectedItem().toString(), hourPicker.getValue() + ":" + minPicker.getValue(), dateInput.getText().toString(), urlInput.getText().toString(), false);
                Controller.getInstance(this.createTaskActivity).createTask(task);
            }

            // If the user is modifying a task, then modify it in the database and the ArrayList
            else {
                Task task = new Task(Controller.getInstance(this.createTaskActivity).getTasksList().get(positionModifiedTask).getId(), titleInput.getText().toString(), descriptionInput.getText().toString(),
                        contextInput.getSelectedItem().toString(), hourPicker.getValue() + ":" + minPicker.getValue(), dateInput.getText().toString(), urlInput.getText().toString(), false);
                Controller.getInstance(this.createTaskActivity).updateTask(task, this.positionModifiedTask);
            }

            // Return to the main activity
            Intent updateIntent = new Intent(this.createTaskActivity, MainActivity.class);
            this.createTaskActivity.startActivityForResult(updateIntent, 1);
            this.createTaskActivity.finish();
        } else {
            Toast.makeText(this.createTaskActivity, "Veuillez préciser le titre et la date.", Toast.LENGTH_LONG).show();
        }
    }

}
