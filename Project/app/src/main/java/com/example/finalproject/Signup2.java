package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Signup2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
    }

    public void onFinish(View view){
        //get the intent
        Intent intent = getIntent();

        //get the type (member or instructor)
        String type = intent.getStringExtra("type");

        //get the fields
        EditText text1 = (EditText) findViewById(R.id.firstname);          //firstname
        EditText text2 = (EditText) findViewById(R.id.lastname);           //lastname
        EditText text3 = (EditText) findViewById(R.id.signup_username);    //username
        EditText text4 = (EditText) findViewById(R.id.signup_pass);        //password
        EditText text5 = (EditText) findViewById(R.id.email);              //email

        //convert the texts to string and trim the whitespace
        String firstname = text1.getText().toString().trim();
        String lastname = text2.getText().toString().trim();
        String username = text3.getText().toString().trim();
        String password = text4.getText().toString().trim();
        String email = text5.getText().toString().trim();


        //if no entry then dont go ahead
        if (firstname.length()==0 || lastname.length()==0 || username.length()==0
                || password.length()==0 || email.length()==0){
            return;
        }

        //make appropriate class
        if(type.equals("Member")){
            Member member = new Member(firstname, lastname, username, password, email);
        }
        else if (type.equals("Instructor")){
            Instructor instructor = new Instructor(firstname, lastname, username, password, email);
        }
    }
}