package com.example.finalproject.member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.finalproject.R;
import com.example.finalproject.baseadmin.Class;
import com.example.finalproject.baseadmin.DataBaseHelper;
import com.example.finalproject.baseadmin.EnrolledClass;

import java.util.ArrayList;

public class Member_ViewMyClasses extends AppCompatActivity {

    private int id;
    private ListView classLV;
    private ArrayList<String> classes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_view_my_classes);

        classLV = findViewById(R.id.myClasses);

        Intent intent = getIntent();

        //get info from intent
        id = intent.getIntExtra("memberId", -1);

        DataBaseHelper dbHelper = new DataBaseHelper(this);

        ArrayList<EnrolledClass> enrolledList = dbHelper.findAllEnrolledClasses(id);


        classes = new ArrayList<>();
        //ArrayList<String> classStartTimes = new ArrayList<>();
        //ArrayList<String> classEndTimes = new ArrayList<>();

        int size = enrolledList.size();
        for(int i = 0; i< size; i++){
            EnrolledClass e_cls = enrolledList.get(i);
            int orgClassId = e_cls.getClassOrgId();
            Class cls = dbHelper.findClass(orgClassId);

            System.out.println(cls.getName());

            int startHour1 = e_cls.getStartHour();
            int startMin1 = e_cls.getStartMin();
            int endHour1 = e_cls.getEndHour();
            int endMin1 = e_cls.getEndMin();

            String startHour = startHour1 < 10 ? "0" + startHour1 : String.valueOf(startHour1);
            String startMin = startMin1 < 10 ? "0" + startMin1 : String.valueOf(startMin1);
            String endHour = endHour1 < 10 ? "0" + endHour1 : String.valueOf(endHour1);
            String endMin = endMin1 < 10 ? "0" + endMin1 : String.valueOf(endMin1);


            String startTime = startHour + ":" + startMin;
            String endTime = endHour + ":" + endMin;
            String name = cls.getName();
            String day = cls.getDay();

            classes.add(name + " (" +  day + " at " + startTime + " - " + endTime + ")");
        }

        ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classes);
        classLV.setAdapter(classAdapter);
    }
}