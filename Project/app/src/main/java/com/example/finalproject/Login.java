package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    private final String admin_username = "admin";
    private final String admin_password = "admin123";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onLogin(View view){
        //get username and password entered and trim
        EditText text1 = (EditText) findViewById(R.id.username);
        EditText text2 = (EditText) findViewById(R.id.password);

        String username = text1.getText().toString().trim();
        String password = text2.getText().toString().trim();

        //if nothing entered do nothing
        if (username.length() == 0 || password.length() == 0){
            return;
        }

        else if (username.equals(admin_username) && password.equals(admin_password)){
            Intent intent = new Intent(getApplicationContext(), AdminPage.class);
            startActivity(intent);
        }

        else{
            DataBaseHelper dbHelper = new DataBaseHelper(Login.this);
            Member foundUser = dbHelper.findUserName(username, password);
            System.out.println(foundUser);

            //if (foundUser){
                Intent intent2 = new Intent(getApplicationContext(), LoginPage.class);




                //intent2.putExtra()
                startActivity(intent2);
            //}
        }
    }
}