package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class InstructorLoginPage extends AppCompatActivity {

    private int instructorId;
    private String firstname;
    private String type;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor_login_page);
        Intent intent = getIntent();
        type = intent.getStringExtra("type").toString();
        firstname = intent.getStringExtra("firstname").toString();
        username = intent.getStringExtra("username").toString();
        instructorId = intent.getIntExtra("instructorId", -1);

        TextView text = (TextView) findViewById(R.id.instructor_name);
        text.append(firstname);
    }

    public void onViewMyClasses(View view){
        Intent intent = new Intent(getApplicationContext(), Instructor_ViewMyClasses.class);
        intent.putExtra("instructorId", instructorId);
        intent.putExtra("name", firstname);
        intent.putExtra("type", type);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void onScheduleClass(View view){
        Intent intent = new Intent(getApplicationContext(), Instructor_ScheduleClassList.class);
        intent.putExtra("instructorId", instructorId);
        intent.putExtra("firstname", firstname);
        intent.putExtra("type", type);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void onEditExistingClass(View view){
        Intent intent = new Intent(getApplicationContext(), Instructor_EditClass.class);
        intent.putExtra("instructorId", instructorId);
        intent.putExtra("name", firstname);
        intent.putExtra("type", type);
        intent.putExtra("username", username);
        startActivity(intent);
    }
    public void onLogout(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}