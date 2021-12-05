package com.example.finalproject.member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.baseadmin.Class;
import com.example.finalproject.baseadmin.DataBaseHelper;

public class Member_ViewMyClass extends AppCompatActivity {

    private int classId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_view_my_class);

        Intent intent = getIntent();
        classId = intent.getIntExtra("classId", -1);

        //get textviews
        TextView name = (TextView) findViewById(R.id.className3);
        TextView description = (TextView) findViewById(R.id.classDesc3);
        TextView time = (TextView) findViewById(R.id.classSched);
        TextView difficulty = (TextView) findViewById(R.id.classDiff2);
        TextView day = (TextView) findViewById(R.id.classDay3);
        TextView capacity = (TextView) findViewById(R.id.classCap3);

        DataBaseHelper dbHelper = new DataBaseHelper(this);

        Class cls = dbHelper.findClass(classId);
        String className = cls.getName();
        String classDescription = cls.getDescription();
        int startHour = cls.getHour();
        int startMin = cls.getMin();
        int endHour = cls.getHour() + 1;
        int endMin = cls.getMin();
        String classDifficulty = cls.getDifficulty();
        String classDay = cls.getDay();
        String classCapacity = String.valueOf(cls.getCapacity());

        //add 0 where needed
        String hour1 = startHour < 10 ? "0" + startHour : String.valueOf(startHour);
        String min1 = startMin < 10 ? "0" + startMin : String.valueOf(startMin);
        String hour2 = endHour < 10 ? "0" + endHour : String.valueOf(endHour);
        String min2 = endMin < 10 ? "0" + endMin : String.valueOf(endMin);

        String classTime = hour1 + ":" + min1 + " - " + hour2 + ":" + min2;

        //add class attributes to screen
        name.append(className);
        description.append(classDescription);
        time.append(classTime);
        difficulty.append(classDifficulty);
        day.append(classDay);
        capacity.append(classCapacity);
    }
}