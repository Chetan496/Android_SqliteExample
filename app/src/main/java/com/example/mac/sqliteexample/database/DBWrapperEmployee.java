package com.example.mac.sqliteexample.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.mac.sqliteexample.model.Employee;

/**
 * Created by MAC on 11/13/16.
 */
/*The purpose of this class is to abstract away the database operations
 * for inserting, updating or deleting a row from the EmployeeTbl
  * This class is specific to the EmployeeTbl only
  * If you have another Table in database, create a separate DBWrapper class for it*/

public class DBWrapperEmployee {


    //inserts the given Employee POJO to DB and returns the id of the row inserted.
    public long insert(final Employee employee, final SQLiteDatabase sqLiteDatabase) {
        ContentValues values = new ContentValues();
        values.put(EmployeeManagementContract.EmployeeTbl.COLUMN_NAME_FIRSTNAME,
                employee.getFirstName());
        values.put(EmployeeManagementContract.EmployeeTbl.COLUMN_NAME_LASTNAME,
                employee.getLastName());
        values.put(EmployeeManagementContract.EmployeeTbl.COLUMN_NAME_DEPARTMENT,
                employee.getDepartment());
        values.put(EmployeeManagementContract.EmployeeTbl.COLUMN_NAME_DESIGNATION,
                employee.getDesignation());

        long newRowId = sqLiteDatabase.insert(EmployeeManagementContract.EmployeeTbl.TABLE_NAME,
                null, values);

        return newRowId; //return the primary Id of the row inserted
        /*it is the responsibility of the caller to set the ID of the Employee pojo, after it
        is inserted*/
    }

    public Cursor getAll(final SQLiteDatabase sqLiteDatabase){
        final String[] projection = {
                EmployeeManagementContract.EmployeeTbl._ID,
                EmployeeManagementContract.EmployeeTbl.COLUMN_NAME_FIRSTNAME,
                EmployeeManagementContract.EmployeeTbl.COLUMN_NAME_LASTNAME,
                EmployeeManagementContract.EmployeeTbl.COLUMN_NAME_DEPARTMENT,
                EmployeeManagementContract.EmployeeTbl.COLUMN_NAME_DESIGNATION
        };

        final String sortOrder = EmployeeManagementContract.EmployeeTbl.COLUMN_NAME_FIRSTNAME
                + " ASC";

        Cursor cursor = sqLiteDatabase.query(
                false,
                EmployeeManagementContract.EmployeeTbl.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder,
                null
        );

        return cursor;
    }

    public Employee queryById(final long id, final SQLiteDatabase sqLiteDatabase) {

        //a projection specifies which column we want to retrieve
        final String[] projection = {
                EmployeeManagementContract.EmployeeTbl._ID,
                EmployeeManagementContract.EmployeeTbl.COLUMN_NAME_FIRSTNAME,
                EmployeeManagementContract.EmployeeTbl.COLUMN_NAME_LASTNAME,
                EmployeeManagementContract.EmployeeTbl.COLUMN_NAME_DEPARTMENT,
                EmployeeManagementContract.EmployeeTbl.COLUMN_NAME_DESIGNATION
        };

        //filter results by ID - filter on the given Id
        final String selection = EmployeeManagementContract.EmployeeTbl._ID + " = ?";
        final String[] selectionArgs = {Long.toString(id)};

        final String sortOrder = EmployeeManagementContract.EmployeeTbl.COLUMN_NAME_FIRSTNAME
                + " DESC";

        /* note: no need to specify the keyword LIMIT*/
        final String limit = " 1";

        Cursor cursor = sqLiteDatabase.query(
                false,
                EmployeeManagementContract.EmployeeTbl.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder,
                limit
        );

        //now navigate the results, and create a pojo of Employee. Then return it
        Employee employee = null;
        if (cursor.moveToFirst()) {
            final long empId = cursor.getLong(
                    cursor.getColumnIndex(EmployeeManagementContract.EmployeeTbl._ID)
            );
            final String firstName = cursor.getString(
                    cursor.getColumnIndex(EmployeeManagementContract.EmployeeTbl.COLUMN_NAME_FIRSTNAME)
            );
            final String lastName = cursor.getString(
                    cursor.getColumnIndex(EmployeeManagementContract.EmployeeTbl.COLUMN_NAME_LASTNAME)
            );
            final String department = cursor.getString(
                    cursor.getColumnIndex(EmployeeManagementContract.EmployeeTbl.COLUMN_NAME_DEPARTMENT)
            );
            final String designation = cursor.getString(
                    cursor.getColumnIndex(EmployeeManagementContract.EmployeeTbl.COLUMN_NAME_DESIGNATION)
            );

            employee = new Employee(firstName,lastName,department,designation);
            employee.setId(empId);
        }

        cursor.close();
        return employee;
    }


    public int update(final Employee employee, final SQLiteDatabase sqLiteDatabase) {

        if(employee == null){
            Log.e("Error", "The passed employee object to update is null");
            throw new NullPointerException("Employee object to update was null");
        }

        final long empId = employee.getId() ;

        if(empId == 0){
            Log.e("Error", "The emp ID is not set");
            throw new NullPointerException("The Employee to update does not have a Id set") ;
        }

        ContentValues values = new ContentValues();
        values.put(EmployeeManagementContract.EmployeeTbl.COLUMN_NAME_FIRSTNAME,
                employee.getFirstName());
        values.put(EmployeeManagementContract.EmployeeTbl.COLUMN_NAME_LASTNAME,
                employee.getLastName());
        values.put(EmployeeManagementContract.EmployeeTbl.COLUMN_NAME_DEPARTMENT,
                employee.getDepartment());
        values.put(EmployeeManagementContract.EmployeeTbl.COLUMN_NAME_DESIGNATION,
                employee.getDesignation());

        final String selection = EmployeeManagementContract.EmployeeTbl._ID + " = ?";
        final String[] selectionArgs = {Long.toString(empId)};

        int count = sqLiteDatabase.update(
                EmployeeManagementContract.EmployeeTbl.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        Log.d("Debug", "Updated "+count+" rows");
        return count; //this should always be 1 for a single Employee object
    }



    public void delete(final long id, final SQLiteDatabase sqLiteDatabase) {
        final String selection = EmployeeManagementContract.EmployeeTbl._ID + " = ?";
        final String[] selectionArgs = {Long.toString(id)};

        sqLiteDatabase.delete(EmployeeManagementContract.EmployeeTbl.TABLE_NAME,
                selection, selectionArgs);

        Log.d("Debug", "Deleted the employee having the given Id");
    }


}
