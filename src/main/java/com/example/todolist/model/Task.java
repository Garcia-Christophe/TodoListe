package com.example.todolist.model;

/**
 * This class will represent a single task which contain different parameter :
 * - id : The identification number of the task
 * - title : The title of the task
 * - description : The details of the task
 * - context : The context of the task
 * - duration : The duration of the task in minutes
 * - date : The date of the task
 * - url : The link of the task
 * - isChecked : true if the task is checked
 *
 * @author Sullivan Leboeuf, Christophe Garcia
 * @version 1.0
 */
public class Task {

    /**
     * The task identification number.
     */
    private int id;

    /**
     * The task title.
     */
    private String title;

    /**
     * The task description.
     */
    private String description;

    /**
     * The task context.
     */
    private String context;

    /**
     * The task duration.
     */
    private String duration;

    /**
     * The task date.
     */
    private String date;

    /**
     * The task URL.
     */
    private String url;

    /**
     * The state of the task check box.
     */
    private boolean isChecked;

    /**
     * Constructor with all attributes
     *
     * @param id - the task id
     * @param title - the task title
     * @param description - the task description
     * @param context - the task context
     * @param duration - the task duration
     * @param date - the task date
     * @param url - the task link
     */
    public Task(int id, String title, String description, String context, String duration, String date, String url, boolean isChecked){
        if (title != null && date != null) {
            this.setId(id);
            this.setTitle(title);
            this.setDescription(description);
            this.setContext(context);
            this.setDuration(duration);
            this.setDate(date);
            this.setUrl(url);
            this.setIsChecked(isChecked);
        }
    }

    /**
     * Get the task id
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the task id
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the task title
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the task title
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the task description
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the task description
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description == null ? "" : description;
    }

    /**
     * Get the task context
     *
     * @return the context
     */
    public String getContext() {
        return context;
    }

    /**
     * Set the task context
     *
     * @param context the context to set
     */
    public void setContext(String context) {
        this.context = context == null ? "" : context;
    }

    /**
     * Get the task duration
     *
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Set the task duration
     *
     * @param duration the duration to set
     */
    public void setDuration(String duration) {
        this.duration = duration == null ? "" : duration;
    }

    /**
     * Get the task date
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Set the task date
     *
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Get the task url
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set the task url
     *
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Get the task check box state
     *
     * @return the state of the check box
     */
    public boolean getIsChecked() {
        return isChecked;
    }

    /**
     * Set the task check box state
     *
     * @param isChecked the state of the check box
     */
    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}