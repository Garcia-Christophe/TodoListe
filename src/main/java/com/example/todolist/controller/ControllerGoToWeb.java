package com.example.todolist.controller;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todolist.CreateTaskActivity;
import com.example.todolist.R;

/**
 * This class is a controller. This controller allows to go to the user default web browser with the url
 * previously given.
 *
 * @author Christophe GARCIA, Sullivan LEBOEUF
 * @version 1.0
 */
public class ControllerGoToWeb implements View.OnClickListener {

    /**
     * The context.
     *
     * @see CreateTaskActivity
     */
    private CreateTaskActivity createTaskActivity;

    /**
     * The constructor creates a new form of ControllerGoToWeb.
     *
     * @param ct the create task activity
     */
    public ControllerGoToWeb(CreateTaskActivity ct) {
        this.createTaskActivity = ct;
    }

    @Override
    public void onClick(View v) {

        // Retrieve the url
        EditText urlView = (EditText) this.createTaskActivity.findViewById(R.id.inputURL);
        String url = urlView.getText().toString();

        // If the text given is a valid URL
        if (URLUtil.isValidUrl(url)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            this.createTaskActivity.startActivity(intent);
        } else {
            Toast.makeText(this.createTaskActivity, "Veuillez saisir une URL valide.", Toast.LENGTH_LONG).show();
        }
    }
}
