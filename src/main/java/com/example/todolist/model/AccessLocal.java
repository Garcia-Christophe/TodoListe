package com.example.todolist.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.todolist.utils.MySQLiteOpenHelper;

import java.util.ArrayList;

/**
 * This class is the local access of the database. It makes the difference between the main
 * {@link com.example.todolist.controller.Controller} and the database.
 *
 * @author Christophe GARCIA, Sullivan LEBOEUF
 * @version 1.0
 */
public class AccessLocal {

    /**
     * The name of the database.
     */
    private String name;

    /**
     * The version number of the database.
     */
    private int version;

    /**
     * The access of the database.
     */
    private MySQLiteOpenHelper accessDB;

    /**
     * The database.
     */
    private SQLiteDatabase db;

    /**
     * The constructor creates a new form of AccessLocal.
     *
     * @param context the context
     */
    public AccessLocal(Context context) {
        this.name = "ToDoList.sqlite";
        this.version = 1;
        this.accessDB = new MySQLiteOpenHelper(context, this.name, null, this.version);
    }

    /**
     * Add a new task to the database.
     *
     * @param task the new task
     */
    public void addTask(Task task) {
        this.db = this.accessDB.getWritableDatabase();
        String query = "INSERT INTO Task (titleTask, descriptionTask, contextTask, durationTask, dateTask, urlTask, isCheckedTask) values";
        query += "(\"" + task.getTitle() + "\", \"" + task.getDescription() + "\", \"" + task.getContext() + "\", \"" +
                task.getDuration() + "\", \"" + task.getDate() + "\", \"" + task.getUrl() + "\", \"" + (task.getIsChecked() ? 1 : 0) + "\")";
        this.db.execSQL(query);
    }

    /**
     * Update the task data.
     *
     * @param task the task to be updated
     */
    public void updateTask(Task task) {
        this.db = this.accessDB.getWritableDatabase();
        String query = "UPDATE Task SET titleTask = \"" + task.getTitle() + "\", " +
                "descriptionTask = \"" + task.getDescription() + "\", " +
                "contextTask = \"" + task.getContext() + "\", " +
                "durationTask = \"" + task.getDuration() + "\"," +
                "dateTask = \"" + task.getDate() + "\"," +
                "urlTask = \"" + task.getUrl() + "\"," +
                "isCheckedTask = \"" + (task.getIsChecked() ? 1 : 0) + "\"" +
                "WHERE idTask = \"" + task.getId() + "\"";
        this.db.execSQL(query);
    }

    /**
     * Delete a task.
     *
     * @param id the identification number of the task to delete
     */
    public void deleteTask(int id) {
        this.db = this.accessDB.getWritableDatabase();
        String query = "DELETE FROM Task WHERE idTask = \"" + id + "\"";
        this.db.execSQL(query);
    }

    /**
     * Delete all tasks.
     */
    public void deleteAllTasks() {
        this.db = this.accessDB.getWritableDatabase();
        String query = "DELETE FROM Task";
        this.db.execSQL(query);
    }

    /**
     * Get the last task added.
     *
     * @return the last task added
     */
    public Task lastTask() {
        this.db = this.accessDB.getReadableDatabase();
        Task task = null;
        String query = "SELECT * FROM Task";
        Cursor cursor = this.db.rawQuery(query, null);
        cursor.moveToLast();

        // If there is a last task, retrieves its data
        if (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String description = cursor.getString(2);
            String context = cursor.getString(3);
            String duration = cursor.getString(4);
            String date = cursor.getString(5);
            String url = cursor.getString(6);
            int isChecked = cursor.getInt(7);

            task = new Task(id, title, description, context, duration, date, url, isChecked == 1);
        }

        cursor.close();
        return task;
    }

    /**
     * Get all tasks
     *
     * @return all tasks
     */
    public ArrayList<Task> allTasks() {
        this.db = this.accessDB.getReadableDatabase();
        ArrayList<Task> taskList = new ArrayList<Task>();
        Task task = null;
        String query = "SELECT * FROM Task";
        Cursor cursor = this.db.rawQuery(query, null);
        cursor.moveToFirst();

        // While the cursor is not pointing after the last task, retrieves the pointed task data
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String description = cursor.getString(2);
            String context = cursor.getString(3);
            String duration = cursor.getString(4);
            String date = cursor.getString(5);
            String url = cursor.getString(6);
            int isChecked = cursor.getInt(7);

            task = new Task(id, title, description, context, duration, date, url, isChecked == 1);
            taskList.add(task);

            cursor.moveToNext();
        }

        cursor.close();
        return taskList;
    }

    /**
     * Add a new context to the database.
     *
     * @param context the new context
     */
    public void addContext(MyContext context) {
        this.db = this.accessDB.getWritableDatabase();
        String query = "INSERT INTO Context (titleContext) values";
        query += "(\"" + context.getTitle() + "\")";
        this.db.execSQL(query);
    }

    /**
     * Delete a context.
     *
     * @param id the identification number of the context to delete
     */
    public void deleteContext(int id) {
        this.db = this.accessDB.getWritableDatabase();
        String query = "DELETE FROM Context WHERE idContext = \"" + id + "\"";
        this.db.execSQL(query);
    }

    /**
     * Get the last context added.
     *
     * @return the last context added
     */
    public MyContext lastContext() {
        this.db = this.accessDB.getReadableDatabase();
        MyContext context = null;
        String query = "SELECT * FROM Context";
        Cursor cursor = this.db.rawQuery(query, null);
        cursor.moveToLast();

        // If there is a last context, retrieves its data
        if (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);

            context = new MyContext(id, title);
        }

        cursor.close();
        return context;
    }

    /**
     * Get all contexts.
     *
     * @return all contexts
     */
    public ArrayList<MyContext> allContexts() {
        this.db = this.accessDB.getReadableDatabase();
        ArrayList<MyContext> contextsList = new ArrayList<MyContext>();
        MyContext context = null;
        String query = "SELECT * FROM Context";
        Cursor cursor = this.db.rawQuery(query, null);
        cursor.moveToFirst();

        // While the cursor is not pointing after the last context, retrieves the pointed context data
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);

            context = new MyContext(id, title);
            contextsList.add(context);

            cursor.moveToNext();
        }

        cursor.close();
        return contextsList;
    }

}
