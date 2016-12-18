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

    public EmployeeAdapter(Context context, int resource, List<Employee> employees) {
        super(context, resource, employees);
        this.resource = resource ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Employee employee = getItem(position);

        if(convertView == null){
            //means we are not reusing an old view..
            //so inflate the view
            convertView = LayoutInflater.from(getContext()).
                    inflate(resource, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvDesignation = (TextView) convertView.findViewById(R.id.tvDesignation);

        String fullName = getFullNameOfEmployee(employee);
        String designation = employee.getDesignation();

        tvName.setText(fullName);
        tvDesignation.setText(designation);

        return  convertView;

    }

    private String getFullNameOfEmployee(Employee employee) {
        StringBuilder sb = new StringBuilder();
        sb.append(employee.getFirstName()).append(" ").append(employee.getLastName());
        return sb.toString();
    }
}
