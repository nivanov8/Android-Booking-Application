package com.example.finalproject.instructor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.finalproject.baseadmin.Class;
import com.example.finalproject.baseadmin.DataBaseHelper;
import com.example.finalproject.R;

import java.util.ArrayList;

public class Instructor_ViewMyClassesList extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<Class> classList;
    ArrayList<String> classNames;
    ArrayList<Integer> classIds;
    ListView classLV;
    int instructorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor_view_my_classes_list);

        Intent intent = getIntent();
        instructorId = intent.getIntExtra("instructorId", -1);
        System.out.println("instructorId: " + instructorId);

        DataBaseHelper dbHelper = new DataBaseHelper(this);

        ArrayList<Class> classList= dbHelper.findTaughtClasses(instructorId);
        classNames = new ArrayList<String>();
        classIds = new ArrayList<Integer>();
        classLV = findViewById(R.id.classes);

        int size = classList.size();

        for (int i = 0; i<size; i++){
            Class cls = classList.get(i);
            String className = cls.getName();
            int classId = classId = cls.getId();
            classIds.add(classId);
            classNames.add(className);
        }

        ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classNames);
        classLV.setAdapter(classAdapter);
        classLV.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getApplicationContext(),Instructor_ViewMyClasses.class);
        int classId = classIds.get(i);
        intent.putExtra("classId", classId);

        startActivity(intent);
    }
}