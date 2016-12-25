package com.example.mac.sqliteexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;



/*Goal is to learn using the SQLite Database in Android applications */
/*Lets create a DB schema with only one table  - Employee.
 * It will have one row pre-inserted..
  * On Activity start, we will load the data from DB for that employee
  * and show on the Activity screen.
  * The form data will be editable.
  * When the user updates and saves the forms, we will update the data back into the DB.
  * The next time that the activity is opened, it will show the updated details of the employee*/


/* TODO: display all the employees in a list view
* use a ArrayAdapter initially, then use the RecyclerView and ViewHolder
* Understand those concepts first*/


/*TODO: learn to use databinding library..there are two way databinding libraries available for android*/

/* TODO: instead of using a empIdto pass the data between activities, you can use a parcelable to pass the whole Employee object*/

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createBtn = (Button) findViewById(R.id.createBtn);
        Button updateBtn = (Button) findViewById(R.id.updateBtn);
        Button viewAllBtn = (Button) findViewById(R.id.viewallemployees);

        createBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateOrUpdateEmployee.class );
            intent.putExtra("empId", 0L);
            startActivity(intent);
        });


        updateBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateOrUpdateEmployee.class );
            intent.putExtra("empId", 1L);
            startActivity(intent);
        });

        viewAllBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListEmployees.class);
            startActivity(intent);
        });



    }
}
