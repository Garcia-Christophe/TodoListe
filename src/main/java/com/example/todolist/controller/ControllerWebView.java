package com.example.todolist.controller;

import android.text.Editable;
import android.text.TextWatcher;

import com.example.todolist.CreateTaskActivity;

/**
 * This class is a controller. This controller allows to see the page with the given url inside
 * the application.
 *
 * @author Christophe GARCIA, Sullivan LEBOEUF
 * @version 1.0
 */
public class ControllerWebView implements TextWatcher {

    /**
     * The context.
     *
     * @see CreateTaskActivity
     */
    private CreateTaskActivity createTaskActivity;

    /**
     * The constructor creates a new form of ControllerWebView.
     *
     * @param ct the create task activity
     */
    public ControllerWebView(CreateTaskActivity ct) {
        this.createTaskActivity = ct;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {

        // Try to load the page each time the url is changed
        this.createTaskActivity.getWebView().loadUrl(this.createTaskActivity.getUrlInput().getText().toString());
    }

}
