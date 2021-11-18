package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Instructor_ScheduleClassList extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayList<Class> classList;
    private ArrayList<String> classNames;
    private ArrayList<Integer> classIds;
    private ListView classLV;
    private int instructorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor_view_all_classes);

        //get the instructor id
        Intent intent = getIntent();
        instructorId = intent.getIntExtra("id", -1);

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
        int id = classIds.get(i);
        String name = adapterView.getItemAtPosition(i).toString();
        Intent intent = new Intent(getApplicationContext(), Instructor_ScheduleClass.class);

        intent.putExtra("classId", id);
        intent.putExtra("name", name);
        intent.putExtra("instructorId", instructorId);
        startActivity(intent);

    }
}