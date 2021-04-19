package com.example.todolist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.todolist.controller.Controller;
import com.example.todolist.controller.ControllerGoToWeb;
import com.example.todolist.controller.ControllerSaveTask;
import com.example.todolist.controller.ControllerWebView;
import com.example.todolist.model.MyContext;
import com.example.todolist.utils.MyWebViewClient;
import com.example.todolist.utils.WebAppInterface;

import java.util.Calendar;

/**
 * This activity allows to create, modify and see a task.
 *
 * @author Christophe GARCIA, Sullivan LEBOEUF
 * @version 1.0
 */
public class CreateTaskActivity extends AppCompatActivity {

    /**
     * The web view in the form. It allows to see a web page without going to a external web browser
     */
    private WebView webView;

    /**
     * The url input in the form.
     */
    private EditText urlInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        // The hour and minute pickers
        NumberPicker hourPicker = (NumberPicker) findViewById(R.id.hourPicker);
        NumberPicker minutePicker = (NumberPicker) findViewById(R.id.minPicker);
        hourPicker.setMinValue(0);
        hourPicker.setMaxValue(99);
        hourPicker.setWrapSelectorWheel(true);
        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(59);
        minutePicker.setWrapSelectorWheel(true);

        // The url input
        this.urlInput = (EditText) findViewById(R.id.inputURL);
        ControllerWebView controllerWebView = new ControllerWebView(this);
        this.urlInput.addTextChangedListener(controllerWebView);

        // The button allowing to go to the defaut web browser
        Button searchButton = (Button) findViewById(R.id.searchButton);

        // The web view
        this.webView = (WebView) findViewById(R.id.webView);
        ControllerGoToWeb controllerGoToWeb = new ControllerGoToWeb(this);
        searchButton.setOnClickListener(controllerGoToWeb);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        this.webView.addJavascriptInterface(new WebAppInterface(this), "Android");
        this.webView.setWebViewClient(new MyWebViewClient());

        // The date part
        Button chooseDate = (Button) findViewById(R.id.chooseDateButton);
        TextView textDate = (TextView) findViewById(R.id.dateInput);

        // The "on click" listener which formats the date choose by the user
        chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateTaskActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                textDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        // The spinner which proposes all the contexts available
        Spinner contextInput = (Spinner) findViewById(R.id.contextInput);
        ArrayAdapter<MyContext> adapterSpinner = new ArrayAdapter<MyContext>(this, android.R.layout.simple_spinner_dropdown_item, Controller.getInstance(this).getContextsList());
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        contextInput.setAdapter(adapterSpinner);

        // Check if the user wants to modify or see a task (not create)
        EditText textTitle = (EditText) findViewById(R.id.titleInput);
        TextView textDescription = (TextView) findViewById(R.id.descriptionInput);
        Bundle bundle = getIntent().getExtras();
        ControllerSaveTask controllerSaveTask;

        // if the user clicked on a task
        if (bundle != null) {
            textTitle.setText(bundle.getString("title"));
            textDescription.setText(bundle.getString("description"));
            String duration = bundle.getString("duration");
            String[] tab = duration.split(":");
            hourPicker.setValue(Integer.parseInt(tab[0]));
            minutePicker.setValue(Integer.parseInt(tab[1]));
            textDate.setText(bundle.getString("date"));
            String contextText = bundle.getString("context");
            int positionContext = 0;
            while (positionContext < Controller.getInstance(this).getContextsList().size() && !contextText.equalsIgnoreCase(Controller.getInstance(this).getContextsList().get(positionContext).getTitle())) {
                positionContext++;
            }
            if (positionContext < Controller.getInstance(this).getContextsList().size()) {
                contextInput.setSelection(positionContext);
            } else {
                contextInput.setSelection(0);
            }
            urlInput.setText(bundle.getString("url"), null);

            int position = bundle.getInt("position");
            controllerSaveTask = new ControllerSaveTask(this, false, position);
        } else {
            controllerSaveTask = new ControllerSaveTask(this, true, -1);
        }

        // Set the save button listener
        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(controllerSaveTask);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TextView titleInput = (TextView) this.findViewById(R.id.titleInput);
        TextView descriptionInput = (TextView) this.findViewById(R.id.descriptionInput);
        Spinner contextInput = (Spinner) this.findViewById(R.id.contextInput);
        TextView dateInput = (TextView) this.findViewById(R.id.dateInput);
        NumberPicker hourPicker = (NumberPicker) this.findViewById(R.id.hourPicker);
        NumberPicker minPicker = (NumberPicker) this.findViewById(R.id.minPicker);
        TextView urlInput = (TextView) this.findViewById(R.id.inputURL);

        // Set the information of the task clicked on
        titleInput.setText(data.getExtras().getString("title"));
        descriptionInput.setText(data.getExtras().getString("description"));
        contextInput.setSelection(0);
        dateInput.setText(data.getExtras().getString("date"));
        hourPicker.setValue(Integer.parseInt(data.getExtras().getString("hour")));
        minPicker.setValue(Integer.parseInt(data.getExtras().getString("min")));
        urlInput.setText(data.getExtras().getString("url"));
    }

    /**
     * Get the web view
     *
     * @return the web view
     */
    public WebView getWebView() {
        return webView;
    }

    /**
     * Get the url input
     *
     * @return the url input
     */
    public EditText getUrlInput() {
        return urlInput;
    }

}