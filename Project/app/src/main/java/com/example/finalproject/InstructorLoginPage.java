package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class InstructorLoginPage extends AppCompatActivity {

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor_login_page);
        Intent intent = getIntent();

        String type = intent.getStringExtra("type").toString();
        String firstname = intent.getStringExtra("firstName").toString();
        String username = intent.getStringExtra("username").toString();
        id = intent.getIntExtra("id", -1);

        TextView text = (TextView) findViewById(R.id.instructor_name);
        text.append(firstname);
    }

    public void onViewAllClasses(View view){
        Intent intent = new Intent(getApplicationContext(), Instructor_ViewAllClasses.class);
        startActivity(intent);
    }

    public void onScheduleClass(View view){
        Intent intent = new Intent(getApplicationContext(), Instructor_ScheduleClassList.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void onEditExistingClass(View view){
        Intent intent = new Intent(getApplicationContext(), Instructor_EditClass.class);
        startActivity(intent);
    }
}