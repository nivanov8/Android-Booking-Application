package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class Instructor_ScheduleClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor_schedule_class);
        popSpinner1();
        popSpinner2();
        popSpinner3();
        popSpinner4();

    }

    public void onSchedule(View view){
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        String word = spinner1.getSelectedItem().toString().trim();
        System.out.println(word);

        finish();

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

    //make options for spinner3 (hours)
    public void popSpinner3(){
        ArrayList<Integer> hours = new ArrayList<Integer>();
        for(int i=0; i<= 24; i++){
            hours.add(i);
        }

        Spinner spinner = (Spinner) findViewById(R.id.spinner3);

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, hours);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    //make options for spinner4(minutes)
    public void popSpinner4(){
        ArrayList<Integer> mins = new ArrayList<Integer>();
        for (int i =0; i<=60; i++){
            mins.add(i);
        }

        Spinner spinner = (Spinner) findViewById(R.id.spinner4);

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, mins);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}