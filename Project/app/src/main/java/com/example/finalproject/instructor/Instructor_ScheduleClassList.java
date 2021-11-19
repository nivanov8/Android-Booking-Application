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

public class Instructor_ScheduleClassList extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayList<Class> classList;
    private ArrayList<String> classNames;
    private ArrayList<Integer> classIds;
    private ListView classLV;
    private int instructorId;
    private String username;
    private String firstname;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor_view_my_classes_list);
        System.out.println("HIT");

        //get the instructor id
        Intent intent = getIntent();
        instructorId = intent.getIntExtra("instructorId", -1);
        username = intent.getStringExtra("username").toString();
        firstname = intent.getStringExtra("firstname").toString();
        type = intent.getStringExtra("type").toString();

        DataBaseHelper dbHelper = new DataBaseHelper(this);

        classList = dbHelper.getAllClasses();
        classNames = new ArrayList<String>();
        classIds = new ArrayList<Integer>();
        classLV = findViewById(R.id.classes);

        int size = classList.size();
        System.out.println(size);
        for (int i = 0; i<size; i++){
            classIds.add(classList.get(i).getId());
        }

        ArrayList<Integer> tempArr = new ArrayList<Integer>();
        for (int i =0; i < size; i++){
            int classId = classIds.get(i);
            //System.out.println("CLASS ID: " + classId);
            boolean classIsTaught = dbHelper.isTaught(classId);
            if(!classIsTaught){
                System.out.println(classId);
                Class cls = dbHelper.findClass(classId);
                String className = cls.getName();
                classNames.add(className);
                tempArr.add(classId);
            }
        }

        classIds = tempArr;

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
        intent.putExtra("className", name);
        intent.putExtra("instructorId", instructorId);
        intent.putExtra("type", type);
        intent.putExtra("firstname", firstname);
        intent.putExtra("username", username);
        //finish();
        startActivity(intent);
        //System.out.println("We are here");
    }
}