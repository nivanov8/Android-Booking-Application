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
        setContentView(R.layout.instructor_view_my_classes);

        Intent intent = getIntent();
        instructorId = intent.getIntExtra("instructorId", -1);
        System.out.println("instructorId: " + instructorId);

        DataBaseHelper dbHelper = new DataBaseHelper(this);

        ArrayList<Class> classList= dbHelper.findTaughtClasses(instructorId);
        classNames = new ArrayList<String>();
        classLV = findViewById(R.id.classes);

        int size = classList.size();
        System.out.println("SIZE: " + size);


        for (int i = 0; i<size; i++){
            Class cls = classList.get(i);
            String className = cls.getName();
            System.out.println(className);
            classNames.add(className);
        }

        ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classNames);
        classLV.setAdapter(classAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}