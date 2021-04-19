package com.example.todolist.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * This class allows to create the database.
 *
 * @author Christophe GARCIA, Sullivan LEBOEUF
 * @version 1.0
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    /**
     * Script to create the Task table of the database.
     *
     * @see com.example.todolist.model.Task
     */
    private final String CREATION_TASK = "CREATE TABLE Task\n" +
            "  (\n" +
            "    idTask INTEGER PRIMARY KEY autoincrement,\n" +
            "\n" +
            "    titleTask TEXT(100)\n" +
            "    CONSTRAINT nn_titleTask NOT NULL,\n" +
            "\n" +
            "    descriptionTask TEXT(1000),\n" +
            "\n" +
            "    contextTask TEXT(100),\n" +
            "\n" +
            "    durationTask TEXT(5),\n" +
            "\n" +
            "    dateTask TEXT(20)\n" +
            "    CONSTRAINT nn_dateTask NOT NULL," +
            "\n" +
            "   urlTask TEXT(1000)," +
            "\n" +
            "   isCheckedTask BOOLEAN NOT NULL CHECK (isCheckedTask IN (0, 1))\n" +
            "    );" +
            "\n";

    /**
     *  Script to create the Context table of the database.
     *
     * @see com.example.todolist.model.MyContext
     */
    private final String CREATION_CONTEXT = "CREATE TABLE Context\n" +
            "  (\n" +
            "    idContext INTEGER PRIMARY KEY autoincrement,\n" +
            "\n" +
            "    titleContext TEXT(100)\n" +
            "    CONSTRAINT nn_titleContext NOT NULL\n" +
            ");" +
            "\n";

    /**
     * The constructor creates a new form of SQLiteOpenHelper.
     *
     * @param context the context
     * @param name the database name
     * @param factory the subclass when making requests
     * @param version the version number of the database
     */
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * If the database changes.
     *
     * @param db the database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(this.CREATION_TASK);
        db.execSQL(this.CREATION_CONTEXT);

        String query = "INSERT INTO Context (titleContext) values";
        query += "(\"Autres\")";
        db.execSQL(query);
    }

    /**
     * If version changes.
     *
     * @param db the database
     * @param oldVersion the number of the old version
     * @param newVersion the number of the new version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No redefinition of the method
    }
}
