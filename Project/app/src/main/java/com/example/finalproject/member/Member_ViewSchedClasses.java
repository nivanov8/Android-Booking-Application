package com.example.finalproject.member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.finalproject.R;
import com.example.finalproject.baseadmin.Class;
import com.example.finalproject.baseadmin.DataBaseHelper;
import com.example.finalproject.instructor.Instructor_ViewMyClasses;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Member_ViewSchedClasses extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView classLV;
    private String type;
    private String firstname;
    private String username;
    private int id;
    private ArrayList<Class> classList;
    private ArrayList<String> classNames;
    private ArrayList<Integer> classIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_view_sched_classes);

        //get member info
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        firstname = intent.getStringExtra("firstname");
        username = intent.getStringExtra("username");
        id = intent.getIntExtra("memberId", -1);

        classLV = findViewById(R.id.classLV);
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        classList = dbHelper.getAllScheduledClasses();

        classNames = new ArrayList<>();
        classIds = new ArrayList<>();

        int size = classList.size();
        for(int i =0; i< size; i++){
            Class cls = classList.get(i);
            classNames.add(cls.getName()+ " (" + cls.getDay() + ")");
            System.out.println(cls.getId());
            classIds.add(cls.getId());
        }

        for(int i = 0; i<classIds.size(); i++){
            System.out.println("item" + i + " " + classIds.get(i));
        }

        ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classNames);
        classLV.setAdapter(classAdapter);
        classLV.setOnItemClickListener(this);
    }


    public void onViewAllClasses(View view){
        ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classNames);
        classLV.setAdapter(classAdapter);
        classLV.setOnItemClickListener(this);
    }

    public void onSearch(View view){
        EditText text = (EditText) findViewById(R.id.searchBy);
        String input = text.getText().toString().trim();

        //if nothing is entered
        if (input.equals("")){
            Toast.makeText(this, "Enter class name or instructor name to search", Toast.LENGTH_SHORT).show();
            return;
        }

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        ArrayList<Class> list = dbHelper.findAllScheduledClassesMember(input);

        ArrayList<String> names = new ArrayList<String>();

        int size = list.size();
        for(int i=0;i<size;i++){
            Class cls = list.get(i);
            names.add(cls.getName() + " (" + cls.getDay() + ")");
        }
        ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        classLV.setAdapter(classAdapter);
        classLV.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getApplicationContext(), Member_ViewClassInfo.class);

        int classId = classIds.get(i);
        //System.out.println(i);
        //int classId = classIds.get(i);
        //System.out.println(classId);
        intent.putExtra("classId", classId);

        startActivity(intent);
    }


}