package com.example.mac.sqliteexample;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.mac.sqliteexample.database.DBWrapperEmployee;
import com.example.mac.sqliteexample.database.EmployeeManagementDBHelper;
import com.example.mac.sqliteexample.database.EmployeeManagementDBHelperSingleton;
import com.example.mac.sqliteexample.model.Employee;

public class CreateOrUpdateEmployee extends Activity {

    private long empId;
    private EmployeeManagementDBHelper employeeManagementDBHelper;
    private DBWrapperEmployee dbWrapperEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_or_update_employee);

        Bundle extras = getIntent().getExtras() ;
        //assuming that the empId was passed by the previous activity in the intent.
        if(extras != null){
            this.empId = extras.getLong("empId");
            updateBtnLabel(this.empId);
        }else{
            throw new NullPointerException("could not get extras");
        }
        //set on click listener for the button
        findViewById(R.id.createOrUpdateBtn).
                setOnClickListener(new CreateOrUpdateBtnClickListener());

        employeeManagementDBHelper=
                EmployeeManagementDBHelperSingleton.getDBHelperInstance(this);

        dbWrapperEmployee = new DBWrapperEmployee();

    }

    /*here we are updating the btn label dynamically based on empId */
    private void updateBtnLabel(long empId){
        Button btn =  (Button)findViewById(R.id.createOrUpdateBtn) ;
        if(empId == 0L){
            btn.setText("Create");
        }else{
            btn.setText("Update");
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

        //if it is an update, get the Employee data from DB , and bind to the form fields
        if(empId != 0L){

            SQLiteDatabase sqLiteDatabase=
                   employeeManagementDBHelper.getReadableDatabase();

            Employee employee = dbWrapperEmployee.queryById(empId,sqLiteDatabase);
            sqLiteDatabase.close();

            bindEmployeeObjectToUIFields(employee);
        }
    }

    /**
     * We will map the Employee POJO to the form fields
     * @param employee
     */
    private void bindEmployeeObjectToUIFields(final Employee employee) {
        if(employee == null){
            throw new Error("Employee instance is null. Cannot bind to UI widgets");
        }

        TextView firstNameWidget = (TextView) findViewById(R.id.firstName);
        TextView lastNameWidget = (TextView) findViewById(R.id.lastName);
        TextView departmentWidget = (TextView) findViewById(R.id.department);
        TextView designationWidget = (TextView) findViewById(R.id.designation);

        firstNameWidget.setText(employee.getFirstName());
        lastNameWidget.setText(employee.getLastName());
        departmentWidget.setText(employee.getDepartment());
        designationWidget.setText(employee.getDesignation());

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
        dbWrapperEmployee= null;
        employeeManagementDBHelper.close();
    }

    private class CreateOrUpdateBtnClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            //first validate the form fields.
            //the form fields must not be empty

            SQLiteDatabase sqLiteDatabase =
                    employeeManagementDBHelper.getWritableDatabase();

            DBWrapperEmployee dbWrapperEmployee = new DBWrapperEmployee();
            Employee employee = getEmployeeDataFromUIWidgets();

            if(empId != 0){
                //update record
                dbWrapperEmployee.update(employee,sqLiteDatabase);
            }else{
                //create new record
                dbWrapperEmployee.insert(employee,sqLiteDatabase);
            }

            CreateOrUpdateEmployee.this.finish();

        }
    }

    /*had we used a data-binding library, we wont need this */
    /*TODO: add UI validations */
    private Employee getEmployeeDataFromUIWidgets() {
        TextView firstNameWidget = (TextView) findViewById(R.id.firstName);
        TextView lastNameWidget = (TextView) findViewById(R.id.lastName);
        TextView departmentWidget = (TextView) findViewById(R.id.department);
        TextView designationWidget = (TextView) findViewById(R.id.designation);

        Employee employee = new Employee();
        employee.setId(empId);
        employee.setFirstName(firstNameWidget.getText().toString());
        employee.setLastName(lastNameWidget.getText().toString());
        employee.setDepartment(departmentWidget.getText().toString());
        employee.setDesignation(designationWidget.getText().toString());

        return employee;
    }
}
