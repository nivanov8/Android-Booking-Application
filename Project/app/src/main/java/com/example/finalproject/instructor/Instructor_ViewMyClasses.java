package com.example.finalproject.instructor;

import androidx.appcompat.app.AppCompatActivity;
import com.example.finalproject.baseadmin.Class;
import com.example.finalproject.baseadmin.DataBaseHelper;
import com.example.finalproject.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class Instructor_ViewMyClasses extends AppCompatActivity {

    private int classId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor_view_my_classes);
        Intent intent = getIntent();

        classId = intent.getIntExtra("classId", -1);

        //get the textviews
        TextView name = (TextView) findViewById(R.id.name);
        TextView description = (TextView) findViewById(R.id.description);
        TextView time = (TextView) findViewById(R.id.time);
        TextView difficulty = (TextView) findViewById(R.id.difficulty);
        TextView day = (TextView) findViewById(R.id.day);
        TextView capacity = (TextView) findViewById(R.id.capacity);

        DataBaseHelper dbHelper = new DataBaseHelper(this);

        //get class attributes
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