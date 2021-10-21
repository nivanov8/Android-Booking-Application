package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
            Toast.makeText(this, "Nothing Entered/Invalid Input", Toast.LENGTH_SHORT).show();
            return;
        }

        else if (username.equals(admin_username) && password.equals(admin_password)){
            Intent intent = new Intent(getApplicationContext(), AdminPage.class);
            startActivity(intent);
        }

        else{
            DataBaseHelper dbHelper = new DataBaseHelper(Login.this);
            User foundUser = dbHelper.findUser(username, password);

            if (foundUser instanceof Member){
                Member user = (Member) foundUser;

                Intent intent = new Intent(getApplicationContext(), LoginPage.class);
                String fname = user.getFirstname();
                String uname = user.getUsername();

                intent.putExtra("type", "member");
                intent.putExtra("firstName", fname);
                intent.putExtra("username", uname);
                startActivity(intent);
            }
            else if (foundUser instanceof Instructor){
                Instructor user = (Instructor) foundUser;

                Intent intent = new Intent(getApplicationContext(), LoginPage.class);
                String fname = user.getFirstname();
                String uname = user.getUsername();

                intent.putExtra("type", "instructor");
                intent.putExtra("firstName", fname);
                intent.putExtra("username", uname);
                startActivity(intent);
            }
            //found member is null meaning user is not in DB
            else{
                Toast.makeText(this, "User Not Found", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }
}