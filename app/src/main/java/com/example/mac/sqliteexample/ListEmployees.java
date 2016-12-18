package com.example.mac.sqliteexample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.example.mac.sqliteexample.model.Employee;

import java.util.ArrayList;


/*lets show all the employees in the local storage in a list view */
public class ListEmployees extends Activity {
    ArrayList<Employee> employeeArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_employees);

        employeeArrayList = new ArrayList<>();
        //lets add some dummy data
        //ideally we should get this from local DB
        Employee e1 = new Employee("Chetan", "Yewale", "Engineering", "Architect");
        Employee e2 = new Employee("John", "Doe", "Engineering", "Consultant");
        employeeArrayList.add(e1);
        employeeArrayList.add(e2);
    }

    @Override
    protected void onStart() {
        super.onStart();


        //intialize the adapter to use for list view
        EmployeeAdapter employeeAdapter = new EmployeeAdapter(this,
                R.layout.employee_list_view_template,
                employeeArrayList);

        ListView listView = (ListView) findViewById(R.id.employeeListView);
        listView.setAdapter(employeeAdapter);

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
