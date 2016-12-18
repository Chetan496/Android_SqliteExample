package com.example.mac.sqliteexample.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MAC on 12/17/16.
 */

 /*This is the place to define the create and update operations of the database.
 This will be executed every time the database is created/upgraded..
 Consider this as a DB schema setup for our app
     * Currently, in this simple example, we are dealing with only one Table
      * in a real-world app, there may be many tables*/

public class EmployeeManagementDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "EmployeeManagement.db";

    public EmployeeManagementDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION) ;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(EmployeeManagementContract.SQL_CREATE_EMPLOYEE_TBL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(EmployeeManagementContract.SQL_DELETE_EMPLOYEE_TBL); /*just drop and recreate the table */
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
