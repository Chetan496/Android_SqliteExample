package com.example.mac.sqliteexample;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import com.example.mac.sqliteexample.database.DBWrapperEmployee;
import com.example.mac.sqliteexample.database.EmployeeManagementDBHelper;
import com.example.mac.sqliteexample.database.EmployeeManagementDBHelperSingleton;


/*lets show all the employees in the sqlite db in a list view */
public class ListEmployees extends Activity {

    private EmployeeManagementDBHelper employeeManagementDBHelper;
    private DBWrapperEmployee dbWrapperEmployee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_employees);

        employeeManagementDBHelper = EmployeeManagementDBHelperSingleton.getDBHelperInstance(this);
        dbWrapperEmployee = new DBWrapperEmployee();
    }

    @Override
    protected void onStart() {
        super.onStart();

        SQLiteDatabase sqLiteDatabase = employeeManagementDBHelper.getReadableDatabase();
        Cursor cursor = dbWrapperEmployee.getAll(sqLiteDatabase);
        EmployeeCursorAdapter employeeCursorAdapter = new EmployeeCursorAdapter(this, cursor);

        ListView listView = (ListView) findViewById(R.id.employeeListView);
        listView.setAdapter(employeeCursorAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
