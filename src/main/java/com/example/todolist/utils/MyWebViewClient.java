package com.example.todolist.utils;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Recreates a WebViewClient interface to override the shouldOverrideUrlLoading method.
 *
 * @author Christophe GARCIA, Sullivan LEBOEUF
 * @version 1.0
 */
public class MyWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        // Load the url given in the webview given
        view.loadUrl(url);
        return true;
    }

}
