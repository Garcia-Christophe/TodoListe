package com.example.todolist.controller;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolist.ContextActivity;
import com.example.todolist.R;
import com.example.todolist.model.MyContext;

/**
 * This class is a controller. This controller allows to create indirectly a context (MyContext).
 *
 * @author Christophe GARCIA, Sullivan LEBOEUF
 * @version 1.0
 */
public class ControllerAddContext implements View.OnClickListener {

    /**
     * The context.
     *
     * @see ContextActivity
     */
    private ContextActivity contextActivity;

    /**
     * The constructor creates a new form of ControllerAddContext.
     *
     * @param ca the context activity
     */
    public ControllerAddContext(ContextActivity ca) {
        this.contextActivity = ca;
    }

    @Override
    public void onClick(View v) {
        TextView titleInput = (TextView) this.contextActivity.findViewById(R.id.inputNewContext);

        // Check whether the title has been entered
        if (titleInput.getText().toString() != null && titleInput.getText().toString().length() > 0) {
            boolean alreadyExisting = false;

            // The user cannot creates with a name already used
            for (MyContext context : Controller.getInstance(this.contextActivity).getContextsList()) {
                if (context.getTitle().equalsIgnoreCase(titleInput.getText().toString())) {
                    alreadyExisting = true;
                }
            }

            if (!alreadyExisting) {
                MyContext context = new MyContext(0, titleInput.getText().toString());
                Controller.getInstance(this.contextActivity).createContext(context);
                this.contextActivity.updateContextsList();

                titleInput.setText("");
            } else {
                Toast.makeText(this.contextActivity, "Ce contexte existe déjà.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this.contextActivity, "Veuillez saisir le nom du contexte.", Toast.LENGTH_LONG).show();
        }
    }
}
