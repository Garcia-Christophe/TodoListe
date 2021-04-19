package com.example.todolist.utils;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * This class allows to add Javascript Interface to the web view.
 *
 * @author Christophe GARCIA, Sullivan LEBOEUF
 * @version 1.0
 */
public class WebAppInterface {

    /**
     * The context.
     */
    private Context context;

    /**
     * The constructor creates a new form of WebAppInterface.
     *
     * @param c the context
     */
    public WebAppInterface(Context c) {
        this.context = c;
    }

    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(this.context, toast, Toast.LENGTH_SHORT).show();
    }

}
