package com.example.todolist.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.todolist.ContextActivity;
import com.example.todolist.MainActivity;

/**
 * This class is a controller. This controller allows to delete indirectly a context (MyContext).
 *
 * @author Christophe GARCIA, Sullivan LEBOEUF
 * @version 1.0
 */
public class ControllerContextsList implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    /**
     * The context.
     *
     * @see ContextActivity
     */
    private ContextActivity contextActivity;

    /**
     * The constructor creates a new form of ControllerContextsList.
     *
     * @param ca the context activity
     */
    public ControllerContextsList(ContextActivity ca) {
        if (ca != null) {
            this.contextActivity = ca;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Go to the main activity and search tasks wich have the context clicked
        Intent searchByContext = new Intent(this.contextActivity, MainActivity.class);
        searchByContext.putExtra("title", Controller.getInstance(this.contextActivity).getContextsList().get(position).getTitle());
        this.contextActivity.startActivity(searchByContext);
        this.contextActivity.finish();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        // Ask first confirmation to delete the context
        new AlertDialog.Builder(this.contextActivity)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Supprimer")
                .setMessage("Supprimer le contexte : " + Controller.getInstance(this.contextActivity).getContextsList().get(position).getTitle() + " ?")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // The user can not delete the default context ("Autres")
                        if (!Controller.getInstance(ControllerContextsList.this.contextActivity).getContextsList().get(position).getTitle().equalsIgnoreCase("Autres")) {
                            Controller.getInstance(ControllerContextsList.this.contextActivity).deleteContext(position);
                            ControllerContextsList.this.contextActivity.updateContextsList();
                        } else {
                            Toast.makeText(ControllerContextsList.this.contextActivity, "Ce contexte n'est pas supprimable.", Toast.LENGTH_SHORT).show();
                        }
                    }

                })
                .setNegativeButton("Non", null)
                .show();
        return true;
    }

}
