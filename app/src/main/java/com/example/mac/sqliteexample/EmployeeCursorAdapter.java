package com.example.mac.sqliteexample;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.example.mac.sqliteexample.database.EmployeeManagementContract;

/**
 * Created by MAC on 12/25/16.
 */


/*The cursor adapter will help us to bind the list view to the data from the sqlite database */
public class EmployeeCursorAdapter extends CursorAdapter {

    private static class ViewHolder {
        TextView name;
        TextView designation;
    }

    public EmployeeCursorAdapter(Context context, Cursor cursor){
        super(context, cursor, 0);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.employee_list_view_template,
                parent,false);
        Log.d("Debug", "New view called") ;
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.name = (TextView) view.findViewById(R.id.tvName);
        viewHolder.designation = (TextView)view.findViewById(R.id.tvDesignation);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder = (ViewHolder) view.getTag();

        final String firstName = cursor.getString(
                cursor.getColumnIndex(EmployeeManagementContract.EmployeeTbl.COLUMN_NAME_FIRSTNAME));
        final  String lastName = cursor.getString(
                cursor.getColumnIndex(EmployeeManagementContract.EmployeeTbl.COLUMN_NAME_LASTNAME)
        );
        final String designation = cursor.getString(
                cursor.getColumnIndex(EmployeeManagementContract.EmployeeTbl.COLUMN_NAME_DESIGNATION)
        );
        final String fullName = new StringBuilder(firstName).append(" ").append(lastName).toString();

        viewHolder.name.setText(fullName);
        viewHolder.designation.setText(designation);

    }
}
