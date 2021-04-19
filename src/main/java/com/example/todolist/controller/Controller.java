package com.example.todolist.controller;

import android.content.Context;

import com.example.todolist.model.AccessLocal;
import com.example.todolist.model.MyContext;
import com.example.todolist.model.Task;

import java.util.ArrayList;

/**
 * This class is the main controller. This controller is the link between the database part (with {@link AccessLocal}) and the MVC part.
 *
 * @author Christophe GARCIA, Sullivan LEBOEUF
 * @version 1.0
 */
public class Controller {

    /**
     * The unique instance of controller.
     */
    private static Controller instance;

    /**
     * The local access
     */
    private static AccessLocal accessLocal;

    /**
     * The tasks list.
     */
    private static ArrayList<Task> tasksList;

    /**
     * The contexts list (MyContext).
     */
    private static ArrayList<MyContext> contextsList;

    /**
     * The private constructor creates a new form of Controller, it's only called by the static method to
     * get the unique instance.
     */
    private Controller() {
        this.tasksList = Controller.accessLocal.allTasks();
        this.contextsList = Controller.accessLocal.allContexts();
    }

    /**
     * Get the unique instance of Controller.
     *
     * @param context the context
     * @return the unique instance
     */
    public static final Controller getInstance(Context context) {
        if (Controller.instance == null) {
            Controller.accessLocal = new AccessLocal(context);
            Controller.instance = new Controller();
        }

        return Controller.instance;
    }

    /**
     * Call the local access to create a task and add it in the ArrayList.
     *
     * @param task the task to create
     */
    public void createTask(Task task) {
        if (task != null) {
            Controller.accessLocal.addTask(task);
            this.tasksList.add(Controller.accessLocal.lastTask());
        }
    }

    /**
     * Update the task in the ArrayList and call the local access to do it in the database.
     *
     * @param task the task which contains the updated values
     * @param position the position of the task to be updated
     */
    public void updateTask(Task task, int position) {
        if (task != null) {
            this.tasksList.get(position).setTitle(task.getTitle());
            this.tasksList.get(position).setDescription(task.getDescription());
            this.tasksList.get(position).setContext(task.getContext());
            this.tasksList.get(position).setDuration(task.getDuration());
            this.tasksList.get(position).setDate(task.getDate());
            this.tasksList.get(position).setUrl(task.getUrl());
            this.tasksList.get(position).setIsChecked(task.getIsChecked());
            Controller.accessLocal.updateTask(task);
        }
    }

    /**
     * Update the task check box state in the ArrayList and call the local access to update it in the database.
     *
     * @param position modified task position
     * @param checked true if the check box is checked, false otherwise
     */
    public void updateCheckTask(int position, boolean checked) {
        if (position >= 0 && position < this.getTasksList().size()) {
            this.getTasksList().get(position).setIsChecked(checked);
            this.updateTask(this.getTasksList().get(position), position);
        }
    }

    /**
     * Delete a task in the ArrayList and call the local access to delete it from the database.
     *
     * @param position task position in the ArrayList
     */
    public void deleteTask(int position) {
        Controller.accessLocal.deleteTask(this.tasksList.get(position).getId());
        this.tasksList.remove(position);
    }

    /**
     * Delete all the tasks in the ArrayList and call the local access to delete them from the database.
     */
    public void deleteAllTasks() {
        Controller.accessLocal.deleteAllTasks();
        this.tasksList.clear();
    }

    /**
     * Call the local access to create a context and add it in the ArrayList.
     *
     * @param context the context to create
     */
    public void createContext(MyContext context) {
        if (context != null) {
            Controller.accessLocal.addContext(context);
            this.contextsList.add(Controller.accessLocal.lastContext());
        }
    }

    /**
     * Delete a context in the ArrayList and call the local access to delete it from the database.
     *
     * @param position context position in the ArrayList
     */
    public void deleteContext(int position) {
        Controller.accessLocal.deleteContext(this.contextsList.get(position).getId());
        this.contextsList.remove(position);
    }

    /**
     * Get the same tasks list of the database.
     *
     * @return the tasks list
     */
    public ArrayList<Task> getTasksList() {
        return tasksList;
    }

    /**
     * Get the same contexts list (MyContext) of the database.
     *
     * @return the contexts list
     */
    public ArrayList<MyContext> getContextsList() {
        return contextsList;
    }

}
