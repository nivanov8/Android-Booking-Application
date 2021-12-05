package com.example.finalproject.member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.finalproject.R;
import com.example.finalproject.baseadmin.Class;
import com.example.finalproject.baseadmin.DataBaseHelper;
import com.example.finalproject.instructor.Instructor_ScheduleClass;

import java.util.ArrayList;

public class Member_EnrollList extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ArrayList<Class> classList;
    private ArrayList<String> classNames;
    private ArrayList<Integer> classIds;
    private ListView classLV;
    private int memberId;
    private String username;
    private String firstname;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_enroll_list);


        //get the instructor id
        Intent intent = getIntent();
        memberId = intent.getIntExtra("memberId", -1);
        username = intent.getStringExtra("username").toString();
        firstname = intent.getStringExtra("firstname").toString();
        type = intent.getStringExtra("type").toString();
        classLV = findViewById(R.id.classList1);

        classIds = new ArrayList<>();
        classNames = new ArrayList<>();

        DataBaseHelper dbHelper = new DataBaseHelper(this);

        ArrayList<Class> list = dbHelper.getAllScheduledClasses();

        ArrayList<String> names = new ArrayList<String>();

        int size = list.size();
        for(int i=0;i<size;i++){
            Class cls = list.get(i);
            names.add(cls.getName() + " (" + cls.getDay() + " at " + cls.getHour() + ":" + cls.getMin() +
                    " - " + (cls.getHour() + 1) + ":" + cls.getMin() + ")");
            classIds.add(cls.getId());
            classNames.add(cls.getName());
        }
        ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        classLV.setAdapter(classAdapter);
        classLV.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        int id = classIds.get(i);
        String name = classNames.get(i);
        Intent intent = new Intent(getApplicationContext(), Member_EnrollConfirmation.class);

        intent.putExtra("classId", id);
        intent.putExtra("className", name);
        intent.putExtra("memberId", memberId);
        intent.putExtra("type", type);
        intent.putExtra("firstname", firstname);
        intent.putExtra("username", username);

        startActivity(intent);
        //System.out.println("We are here");
    }
}