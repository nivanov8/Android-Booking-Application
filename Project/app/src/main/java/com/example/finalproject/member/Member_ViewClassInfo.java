package com.example.finalproject.member;

import androidx.appcompat.app.AppCompatActivity;
import com.example.finalproject.R;
import com.example.finalproject.baseadmin.Class;
import com.example.finalproject.baseadmin.DataBaseHelper;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Member_ViewClassInfo extends AppCompatActivity {

    private int classId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_view_class_info);

        Intent intent = getIntent();
        classId = intent.getIntExtra("classId", -1);


        //get textviews
        TextView name = (TextView) findViewById(R.id.classname);
        TextView description = (TextView) findViewById(R.id.classDesc);
        TextView time = (TextView) findViewById(R.id.classTime);
        TextView difficulty = (TextView) findViewById(R.id.classDiff);
        TextView day = (TextView) findViewById(R.id.classDay);
        TextView capacity = (TextView) findViewById(R.id.classCap);

        DataBaseHelper dbHelper = new DataBaseHelper(this);

        //get class attributes
        System.out.println(classId);
        Class cls = dbHelper.findClass(classId);
        String className = cls.getName();
        String classDescription = cls.getDescription();
        int classHour = cls.getHour();
        int classMin = cls.getMin();
        String classDifficulty = cls.getDifficulty();
        String classDay = cls.getDay();
        String classCapacity = String.valueOf(cls.getCapacity());
        String classTime = classHour + ":" + classMin;

        //add class attributes to screen
        name.append(className);
        description.append(classDescription);
        time.append(classTime);
        difficulty.append(classDifficulty);
        day.append(classDay);
        capacity.append(classCapacity);
    }
}