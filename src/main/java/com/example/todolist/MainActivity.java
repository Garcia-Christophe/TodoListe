package com.example.todolist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.todolist.controller.AdapterTask;
import com.example.todolist.controller.Controller;
import com.example.todolist.controller.ControllerSearch;
import com.example.todolist.controller.ControllerTasksList;
import com.example.todolist.model.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * This activity represents the main page which shows the tasks list and the options.
 *
 * @author Sullivan LEBOEUF, Christophe GARCIA
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The tasks adapter.
     */
    private AdapterTask adapter;

    /**
     * The view of the tasks list.
     */
    private ListView list;

    /**
     * The search tasks input.
     */
    private TextView searchInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // initialize the tasks ArrayList from the database
        Controller controller = Controller.getInstance(this);
        ArrayList<Task> taskList = controller.getTasksList();

        // set the task adapter to the list view
        adapter = new AdapterTask(this, taskList);
        list = (ListView) findViewById(R.id.listView1);
        list.setAdapter(adapter);

        // set the "on click" listener to the tasks list
        ControllerTasksList controllerTasksList = new ControllerTasksList(this);
        list.setOnItemClickListener(controllerTasksList);
        list.setOnItemLongClickListener(controllerTasksList);

        // button "+" which takes the user to the create task activity
        Intent createFormIntent = new Intent(this, CreateTaskActivity.class);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(createFormIntent);
            }
        });

        // Set the search input listener
        searchInput = (TextView) findViewById(R.id.searchTask);
        ControllerSearch controllerSearch = new ControllerSearch(this);
        searchInput.addTextChangedListener(controllerSearch);

        // If the user clicked on a context before, set the url input with the context title
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            this.searchInput.setText(bundle.getString("title"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {

            // if the user wants to delete all his tasks
            case R.id.actions_clear_all:
                Controller.getInstance(this).deleteAllTasks();
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Tout supprimer")
                        .setMessage("Supprimer toutes les tâches ?")
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Controller.getInstance(MainActivity.this).deleteAllTasks();
                                MainActivity.this.updateTasksList();
                                Toast.makeText(MainActivity.this, "Toutes les tâches ont été supprimées.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Non", null)
                        .show();
                return true;

            // if the user wants to see the application information
            case R.id.actions_informations:
                Intent goToInformationsIntent = new Intent(this, InformationsActivity.class);
                startActivity(goToInformationsIntent);
                return true;

            // if the user wants to go to the context page
            case R.id.actions_contexts:
                Intent goToContextsIntent = new Intent(this, ContextActivity.class);
                startActivity(goToContextsIntent);
                return true;
        }

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        // When the user returns to the main page
        super.onActivityResult(requestCode, resultCode, data);
        this.updateTasksList();
        finish();
    }

    /**
     * Update the tasks list.
     */
    public void updateTasksList() {
        this.adapter.notifyDataSetChanged();
    }

    /**
     * Get the search tasks input.
     *
     * @return the search tasks input
     */
    public TextView getSearchInput() {
        return searchInput;
    }

    /**
     * Get the list view of tasks.
     *
     * @return the tasks list view
     */
    public ListView getList() {
        return list;
    }

    /**
     * Get the tasks adapter.
     *
     * @return the tasks adapter
     */
    public AdapterTask getAdapter() {
        return adapter;
    }

    /**
     * Set a new tasks adapter.
     *
     * @param adapter the new adapter
     */
    public void setAdapter(AdapterTask adapter) {
        this.adapter = adapter;
    }

}