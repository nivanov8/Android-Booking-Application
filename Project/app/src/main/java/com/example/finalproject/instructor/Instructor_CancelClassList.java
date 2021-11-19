package com.example.finalproject.instructor;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;
import com.example.finalproject.baseadmin.Class;
import com.example.finalproject.baseadmin.DataBaseHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Instructor_CancelClassList extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<Class> classList;
    ArrayList<String> classNames;
    ArrayList<Integer> classIds;
    ListView classLV;
    int instructorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor_cancel_class_list);

        Intent intent = getIntent();
        instructorId = intent.getIntExtra("instructorId", -1);
        //System.out.println("instructorId: " + instructorId);


        DataBaseHelper dbHelper = new DataBaseHelper(this);

        ArrayList<Class> classList= dbHelper.findTaughtClasses(instructorId);
        classNames = new ArrayList<String>();
        classIds = new ArrayList<Integer>();
        classLV = findViewById(R.id.list);

        int size = classList.size();

        for (int i = 0; i<size; i++){
            Class cls = classList.get(i);
            String className = cls.getName();
            String day = cls.getDay();
            int classId = classId = cls.getId();
            classIds.add(classId);
            classNames.add(className + " (" + day + ")");
        }

        ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classNames);
        classLV.setAdapter(classAdapter);
        classLV.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        int classId = classIds.get(i);
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        dbHelper.deleteTaughtClass(classId);
        dbHelper.deleteClass(classId);
        Toast.makeText(this, "Class cancelled", Toast.LENGTH_SHORT).show();
        finish();
    }
}