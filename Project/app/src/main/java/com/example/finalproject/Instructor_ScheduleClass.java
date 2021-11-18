package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class Instructor_ScheduleClass extends AppCompatActivity {

    private int instructorID;
    private int classId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor_schedule_class);

        Intent intent = getIntent();
        instructorID = intent.getIntExtra("instructorId", -1);
        classId = intent.getIntExtra("classId", -1);

        popSpinner1();
        popSpinner2();

        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker1);
        timePicker.setIs24HourView(true);

    }

    public void onSchedule(View view){
        EditText text = (EditText) findViewById(R.id.class_capacity);
        String cap = text.getText().toString().trim();

        int capacity;
        //check to make sure capacity is not nothing and an integer
        try{
            capacity = Integer.parseInt(cap);
        }
        catch (Exception e){
            Toast.makeText(this, "Enter a valid capacity", Toast.LENGTH_SHORT).show();
            return;
        }


        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        String difficulty = spinner1.getSelectedItem().toString().trim();

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        String day = spinner2.getSelectedItem().toString().trim();

        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker1);
        int hour = timePicker.getHour();
        int min = timePicker.getMinute();

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        dbHelper.updateTeachClass(classId, difficulty, day, hour, min, capacity);

    }

    //making the options for the spinner (difficulty)
    public void popSpinner1(){
        ArrayList<String> options = new ArrayList<String>();
        options.add("beginner");
        options.add("intermediate");
        options.add("advanced");

        Spinner spinner = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    //making options for spinner2 (day of the week)
    public void popSpinner2(){
        ArrayList<String> days = new ArrayList<String>();
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        days.add("Sunday");

        Spinner spinner = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, days);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

}