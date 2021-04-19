package com.example.todolist.controller;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.example.todolist.MainActivity;
import com.example.todolist.model.Task;

import java.util.ArrayList;

/**
 * This class is a controller. This controller allows to find immediately a task by searching data.
 *
 * @author Christophe GARCIA, Sullivan LEBOEUF
 * @version 1.0
 */
public class ControllerSearch implements TextWatcher {

    /**
     * The context.
     *
     * @see MainActivity
     */
    private MainActivity mainActivity;

    /**
     * The constructor creates a new form of ControllerSearch.
     *
     * @param ma the main activity
     */
    public ControllerSearch(MainActivity ma) {
        this.mainActivity = ma;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {

        // Update the list of tasks corresponding of the research
        String search = this.mainActivity.getSearchInput().getText().toString();
        search.trim();
        ArrayList<Task> tasksFound = new ArrayList<Task>();
        boolean found = false;

        // Get all tasks matching with the research
        for (int i = 0; i < Controller.getInstance(this.mainActivity).getTasksList().size(); i++) {

            // If anything has been found (searching in : title, description, context, duration and date)
            if (Controller.getInstance(this.mainActivity).getTasksList().get(i).getTitle().toLowerCase().contains(search.toLowerCase()) ||
                    Controller.getInstance(this.mainActivity).getTasksList().get(i).getDescription().toLowerCase().contains(search.toLowerCase()) ||
                    Controller.getInstance(this.mainActivity).getTasksList().get(i).getContext().toLowerCase().contains(search.toLowerCase()) ||
                    Controller.getInstance(this.mainActivity).getTasksList().get(i).getDuration().toLowerCase().contains(search.toLowerCase()) ||
                    Controller.getInstance(this.mainActivity).getTasksList().get(i).getDate().toLowerCase().contains(search.toLowerCase())) {
                tasksFound.add(Controller.getInstance(this.mainActivity).getTasksList().get(i));
                found = true;
            }
        }

        // If nothing was found, display it
        if (!found) {
            Toast.makeText(this.mainActivity, "Aucune tâche trouvée.", Toast.LENGTH_SHORT).show();
        }

        // Refresh the list view
        this.mainActivity.setAdapter(new AdapterTask(this.mainActivity, tasksFound));
        this.mainActivity.getList().setAdapter(this.mainActivity.getAdapter());
        this.mainActivity.updateTasksList();
    }

}
