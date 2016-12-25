package com.example.mac.sqliteexample;

import android.app.Application;
import com.facebook.stetho.Stetho;

/**
 * Created by MAC on 12/24/16.
 */

public class EmployeeApplication extends Application {

    /*we are using Stetho library from facebook
    to help us debugging. we just need to  initialize it
     so that stetho can be used for debugging it*/
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
