package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // database and dao variable
    MyDatabase db = null;
    UserDAO userDAO = null;
    // Intent variable
    Intent i;
    // shared preference variable
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    // boolean variable for remember me checkbox
    Boolean remember = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = MyDatabase.getDatabase(getApplicationContext());
        userDAO = db.productDao();
        //insert user into db if table of users empty
        int userQuantity = userDAO.getQuantity();
        if(userQuantity == 0){
            userDAO.insert(new User("thanos", "5555"));
            userDAO.insert(new User("wonderwoman", "abcd"));
        }
        i = new Intent(this, HomeActivity.class);

        sp = getSharedPreferences("my-shared-preferences", Context.MODE_PRIVATE);
        editor = sp.edit();

        Intent g = getIntent();
        //get boolean flag from logout button
        Boolean logOutFlag = g.getBooleanExtra("myBoolean", false);
        // if logout flag true delete data in preference
        if(logOutFlag){
            editor.remove("name");
            editor.remove("checkKey");
            editor.apply();
        }
        // if remember flag true user will get username from preference and login automatically
        String username =  sp.getString("name","");
        int checkNum = sp.getInt("checkKey", 0);
        if(checkNum == 1){
            User.shareName = username;
            startActivity(i);
        }
    }

    //login button with validation
    public void logIn(View view) {
        Log.d("ABC", "login to app" );
        Toast t;
        TextView et_name = (TextView) findViewById(R.id.etName);
        TextView et_password = (TextView) findViewById(R.id.etPassword);

        String name = et_name.getText().toString();
        String password = et_password.getText().toString();
        //when user enter name, db return username from db
        User a = userDAO.getUser(name);
        //if user not found in db show message
        if(a == null){
            t = Toast.makeText(this, "The username does not exist", Toast.LENGTH_SHORT);
            t.show();
        }else {
            //condition to check username
            if(name.equalsIgnoreCase(a.username) ){
                //condition to check password
                if(password.equalsIgnoreCase(a.password)){
                    //if user set check in remember me username and variable to login automatically will save in preference
                    if (remember) {
                        editor.putString("name", name);
                        editor.putInt("checkKey", 1);
                        editor.apply();
                    }
                    //set name to static variable in user class
                    User.shareName = name;
                    startActivity(i);

                }else {
                    t = Toast.makeText(this, "The username and password combination is incorrect", Toast.LENGTH_SHORT);
                    t.show();
                }
            }else {
                t = Toast.makeText(this, "The username does not exist", Toast.LENGTH_SHORT);
                t.show();
            }
        }
    }

    public void onCheckboxClicked(View view) {
       CheckBox check = (CheckBox)findViewById(R.id.rm_checkBox);
        if(check.isChecked()){
            remember = true;
            Log.d("ABC", "check box" );
        }
    }
}