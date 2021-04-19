package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.todolist.controller.AdapterContext;
import com.example.todolist.controller.Controller;
import com.example.todolist.controller.ControllerAddContext;
import com.example.todolist.controller.ControllerContextsList;
import com.example.todolist.model.MyContext;

import java.util.ArrayList;

/**
 * This activity allows to create or delete a context
 *
 * @author Christophe GARCIA, Sullivan LEBOEUF
 * @version 1.0
 */
public class ContextActivity extends AppCompatActivity {

    /**
     * The adapter of the contexts list
     */
    private AdapterContext adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context);

        // initialize the context ArrayList from the database
        Controller controller = Controller.getInstance(this);
        ArrayList<MyContext> contextsList = controller.getContextsList();

        // Set the adapter to the contexts list view
        this.adapter = new AdapterContext(this, contextsList);
        ListView list = (ListView) findViewById(R.id.contextsList);
        list.setAdapter(adapter);

        // Set the add button listener
        Button button = (Button) findViewById(R.id.addContextButton);
        button.setOnClickListener(new ControllerAddContext(this));

        // Set the "on click" listener on the context list view to delete them
        ControllerContextsList controllerContextsList = new ControllerContextsList(this);
        list.setOnItemClickListener(controllerContextsList);
        list.setOnItemLongClickListener(controllerContextsList);
    }

    /**
     * Update the contexts list
     */
    public void updateContextsList() {
        this.adapter.notifyDataSetChanged();
    }

}