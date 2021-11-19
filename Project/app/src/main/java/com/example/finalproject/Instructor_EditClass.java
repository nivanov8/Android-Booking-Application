package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

public class Instructor_EditClass extends AppCompatActivity {

    int classId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor_edit_class);

        Intent intent = getIntent();
        classId = intent.getIntExtra("classId", -1);

        popSpinner1();
        popSpinner2();

        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker2);
        timePicker.setIs24HourView(true);
    }

    public void onConfirmChanges(View view){
        EditText text = (EditText) findViewById(R.id.class_capacity1);
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


        Spinner spinner1 = (Spinner) findViewById(R.id.spinner5);
        String difficulty = spinner1.getSelectedItem().toString().trim();

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner4);
        String day = spinner2.getSelectedItem().toString().trim();

        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker2);
        int hour = timePicker.getHour();
        int min = timePicker.getMinute();

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        dbHelper.updateTeachClass(classId, difficulty, day, hour, min, capacity);

        Toast.makeText(this, "Class updated", Toast.LENGTH_SHORT).show();

        finish();
    }

    //making the options for the spinner (difficulty)
    public void popSpinner1(){
        ArrayList<String> options = new ArrayList<String>();
        options.add("beginner");
        options.add("intermediate");
        options.add("advanced");

        Spinner spinner = (Spinner) findViewById(R.id.spinner5);

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

        Spinner spinner = (Spinner) findViewById(R.id.spinner4);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, days);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

}