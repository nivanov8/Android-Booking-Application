package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Instructor_ScheduleClass extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<Class> classList;
    ArrayList<String> classNames;
    ArrayList<Integer> classIds;
    ListView classLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor_view_all_classes);
        DataBaseHelper dbHelper = new DataBaseHelper(this);

        classList = dbHelper.getAllClasses();
        classNames = new ArrayList<String>();
        classIds = new ArrayList<Integer>();
        classLV = findViewById(R.id.classes);

        int size = classList.size();
        System.out.println(size);
        for (int i = 0; i<size; i++){
            System.out.println(classList.get(i).getName());
            classNames.add(classList.get(i).getName());
            classIds.add(classList.get(i).getId());

        }

        ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classNames);
        classLV.setAdapter(classAdapter);

        classLV.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}