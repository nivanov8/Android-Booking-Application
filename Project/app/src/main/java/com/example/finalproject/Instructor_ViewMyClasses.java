package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Instructor_ViewMyClasses extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<Class> classList;
    ArrayList<String> classNames;
    ArrayList<Integer> classIds;
    ListView classLV;
    int instructorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor_view_all_classes);

        Intent intent = getIntent();
        instructorId = intent.getIntExtra("instructorId", -1);


        DataBaseHelper dbHelper = new DataBaseHelper(this);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}