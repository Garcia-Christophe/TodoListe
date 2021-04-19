package com.example.todolist.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.todolist.R;
import com.example.todolist.model.MyContext;

import java.util.List;

/**
 * This class is the contexts adapter, it gets the view of a context (MyContext).
 *
 * @author Christophe GARCIA, Sullivan LEBOEUF
 * @version 1.0
 */
public class AdapterContext extends BaseAdapter {

    /**
     * The contexts list (MyContext).
     */
    private List<MyContext> listContext;

    /**
     * The inflater, used to create a new View (or Layout) object.
     */
    private LayoutInflater inflater;

    /**
     * The constructor creates a new form of AdapterContext.
     *
     * @param context the context
     * @param list the list of contexts (MyContext)
     */
    public AdapterContext(Context context, List<MyContext> list) {
        listContext = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView;

        // Create the view from the layout given if convertView is not null
        if (convertView == null) rootView = (View) inflater.inflate(R.layout.context_item, parent, false);
        else rootView = (View) convertView;

        // Set the title of the context in the view
        TextView titleView = (TextView) rootView.findViewById(R.id.titleContextItem);
        titleView.setText(listContext.get(position).getTitle());

        return rootView;
    }

    @Override
    public int getCount() {
        return this.listContext.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listContext.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
