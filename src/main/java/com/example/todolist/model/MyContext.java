package com.example.todolist.model;

/**
 * This class will represent a single context which contain different parameter :
 * - id : The identification number of the context
 * - title : The title of the context
 *
 * @author Sullivan Leboeuf, Christophe Garcia
 * @version 1.0
 */
public class MyContext {

    /**
     * The context identification number.
     */
    private int id;

    /**
     * The context title.
     */
    private String title;

    /**
     * Constructor with all attributes
     *
     * @param id - the context id
     * @param title - the context title
     */
    public MyContext(int id, String title){
        if (title != null) {
            this.setId(id);
            this.setTitle(title);
        }
    }

    /**
     * Get the context id
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id context
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the context title
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the context title
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.getTitle();
    }

}