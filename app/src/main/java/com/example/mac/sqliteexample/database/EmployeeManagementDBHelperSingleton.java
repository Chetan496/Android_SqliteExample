package com.example.mac.sqliteexample.database;

import android.content.Context;


/**
 * Created by MAC on 12/17/16.
 */

public class EmployeeManagementDBHelperSingleton {

    private static EmployeeManagementDBHelper employeeMgmtDBHelper;

    /* private constructor*/
    private EmployeeManagementDBHelperSingleton(){

    }

    public static EmployeeManagementDBHelper getDBHelperInstance(Context context){
        if(employeeMgmtDBHelper == null){
            employeeMgmtDBHelper = new EmployeeManagementDBHelper(context);
        }

        return employeeMgmtDBHelper;
    }
}
