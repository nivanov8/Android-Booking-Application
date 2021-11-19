package com.example.finalproject.baseadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject.R;

public class Signup2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup2);
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
            Toast.makeText(this, "One or More Fields are Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        //create database object to insert into
        DataBaseHelper db = new DataBaseHelper(Signup2.this);

        //check to make sure no duplicate usernames are made
        boolean isDuplicate = db.duplicateUsername(username);

        if (isDuplicate){
            Toast.makeText(this, "Username already taken", Toast.LENGTH_SHORT).show();
            return;
        }


        //make appropriate class and add to database
        if(type.equals("Member")){
            Member member = db.addMember(firstname, lastname, username, password, email);

            Intent intent2 = new Intent(getApplicationContext(), LoginPage.class);

            //get username and firstname to pass to other view
            String uname = member.getUsername();
            String fname = member.getFirstname();

            intent2.putExtra("type", "member");
            intent2.putExtra("firstname", fname);
            intent2.putExtra("username", uname);

            startActivity(intent2);
        }
        else if (type.equals("Instructor")){
            Instructor instructor = db.addInstructor(firstname, lastname, username, password, email);

            Intent intent2 = new Intent(getApplicationContext(), LoginPage.class);

            //get username and firstname to pass to other view
            String uname = instructor.getUsername();
            String fname = instructor.getFirstname();

            intent2.putExtra("type", "instructor");
            intent2.putExtra("firstname", fname);
            intent2.putExtra("username", uname);

            startActivity(intent2);
        }
    }
}