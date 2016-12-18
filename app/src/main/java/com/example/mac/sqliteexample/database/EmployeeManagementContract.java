package com.example.mac.sqliteexample.database;

import android.provider.BaseColumns;

/**
 * Created by MAC on 11/13/16.
 */

public final class EmployeeManagementContract {

    private EmployeeManagementContract() {
    }

    /*we have inherited the _ID column automatically by inheriting the BaseColumns*/
    /*This class describes the Employee table in the database */
    public static class EmployeeTbl implements BaseColumns{
        public static final String TABLE_NAME  = "Employee";
        public static final String COLUMN_NAME_FIRSTNAME= "firstName";
        public static final String COLUMN_NAME_LASTNAME= "lastName";
        public static final String COLUMN_NAME_DEPARTMENT = "department";
        public static final String COLUMN_NAME_DESIGNATION = "designation";
    }

    /*now lets define some constants */
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    /*constants specific to the EmployeeTbl - these are SQL statements*/
    protected static final String SQL_CREATE_EMPLOYEE_TBL =
            "CREATE TABLE " + EmployeeTbl.TABLE_NAME + " (" +
                    EmployeeTbl._ID + " INTEGER PRIMARY KEY, " +
                    EmployeeTbl.COLUMN_NAME_FIRSTNAME + TEXT_TYPE + COMMA_SEP +
                    EmployeeTbl.COLUMN_NAME_LASTNAME + TEXT_TYPE + COMMA_SEP +
                    EmployeeTbl.COLUMN_NAME_DEPARTMENT + TEXT_TYPE + COMMA_SEP +
                    EmployeeTbl.COLUMN_NAME_DESIGNATION + TEXT_TYPE + " )";

    protected static final String SQL_DELETE_EMPLOYEE_TBL =
            "DROP TABLE IF EXISTS " + EmployeeTbl.TABLE_NAME ;





}
