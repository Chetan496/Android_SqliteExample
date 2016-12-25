package com.example.mac.sqliteexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.mac.sqliteexample.model.Employee;

import java.util.List;

/**
 * Created by MAC on 12/18/16.
 */

/*
This class is for describing how each Employee will be rendered in an item in the list view
 */

public class EmployeeAdapter extends ArrayAdapter<Employee> {

    /* the resource here refers to the layout file that's going to be used for
    * displaying a single view.. in our case the caller should pass it as
    * R.id.employee_list_view_template.xml*/
    private int resource;

    //View lookup cache - for faster view lookup
    // a view holder saves you from calling findViewById when a view is being recycled
    //its a caching mechanism , you can cache the frequently used UI widgets in the list view
    //so its helpful from a performance perspective
    private static class ViewHolder {
        TextView name;
        TextView designation;
    }

    public EmployeeAdapter(Context context, int resource, List<Employee> employees) {
        super(context, resource, employees);
        this.resource = resource ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Employee employee = getItem(position);

        //view lookup cache stored in tag
        ViewHolder viewHolder ;

        if(convertView == null){
            //means we are not reusing an old view..
            //so inflate the view i.e create a new row view

            convertView = LayoutInflater.from(getContext()).
                    inflate(resource, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.designation = (TextView) convertView.findViewById(R.id.tvDesignation);

            //cache the viewHolder object inside the fresh view
            //setTag allows us to attach an arbitrary object onto a View object
            convertView.setTag(viewHolder);

        }else{
            //view is being recycled, get the viewHolder from the cache i.e from the associated tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final String fullName = employee.getFullNameOfEmployee();
        final String designation = employee.getDesignation();

        viewHolder.name.setText(fullName);
        viewHolder.designation.setText(designation);

        return  convertView;

    }

}
