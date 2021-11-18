package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class InstructorLoginPage extends AppCompatActivity {

    private int instructorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor_login_page);
        Intent intent = getIntent();

        String type = intent.getStringExtra("type").toString();
        String firstname = intent.getStringExtra("firstName").toString();
        String username = intent.getStringExtra("username").toString();
        instructorId = intent.getIntExtra("id", -1);

        TextView text = (TextView) findViewById(R.id.instructor_name);
        text.append(firstname);
    }

    public void onViewMyClasses(View view){
        Intent intent = new Intent(getApplicationContext(), Instructor_ViewMyClasses.class);
        intent.putExtra("id", instructorId);
        startActivity(intent);
    }

    public void onScheduleClass(View view){
        Intent intent = new Intent(getApplicationContext(), Instructor_ScheduleClassList.class);
        intent.putExtra("id", instructorId);
        startActivity(intent);
    }

    public void onEditExistingClass(View view){
        Intent intent = new Intent(getApplicationContext(), Instructor_EditClass.class);
        startActivity(intent);
    }
}