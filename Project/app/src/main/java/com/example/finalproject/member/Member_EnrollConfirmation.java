package com.example.finalproject.member;

import androidx.appcompat.app.AppCompatActivity;
import com.example.finalproject.R;
import com.example.finalproject.baseadmin.Class;
import com.example.finalproject.baseadmin.DataBaseHelper;
import com.example.finalproject.baseadmin.EnrolledClass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Member_EnrollConfirmation extends AppCompatActivity {

    private int classId;
    private String className;
    private int memberId;
    private String type;
    private String firstname;
    private String username;

    private int classStartHour;
    private int classStartMin;
    private int classEndHour;
    private int classEndMin;
    private String classDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_enroll_confirmation);

        Intent intent = getIntent();
        classId = intent.getIntExtra("classId", -1);
        className = intent.getStringExtra("className");
        memberId = intent.getIntExtra("memberId", -1);
        type = intent.getStringExtra("type");
        firstname = intent.getStringExtra("firstname");
        username = intent.getStringExtra("username");

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        Class cls = dbHelper.findClass(classId);

        classStartHour = cls.getHour();
        classStartMin = cls.getMin();
        classDay = cls.getDay();


        //set some variables
        classEndHour = classStartHour + 1;
        classEndMin = classStartMin;

        String startHour = classStartHour < 10 ? "0" + classStartHour : String.valueOf(classStartHour);
        String startMin = classStartMin < 10 ? "0" + classStartMin : String.valueOf(classStartMin);
        String endHour = classEndHour < 10 ? "0" + classEndHour : String.valueOf(classEndHour);
        String endMin = classEndMin < 10 ? "0" + classEndMin : String.valueOf(classEndMin);


        //get textviews
        TextView name = (TextView) findViewById(R.id.className1);
        TextView day = (TextView) findViewById(R.id.classDay1);
        TextView startTime = (TextView) findViewById(R.id.classStartTime);
        TextView endTime = (TextView) findViewById(R.id.classEndTime);

        String classStartTimeString = startHour + ":" + startMin;
        String classEndTimeString = endHour + ":" + endMin;

        //add to textviews
        name.append(className);
        day.append(classDay);
        startTime.append(classStartTimeString);
        endTime.append(classEndTimeString);
    }


    public void onConfirm(View view){
        DataBaseHelper dbHelper = new DataBaseHelper(this);

        boolean isSameTime = dbHelper.isAtSameTime(memberId, classStartHour, classStartMin);
        boolean isMaxCap = dbHelper.isMaXCapacity(classId);
        boolean classTakenOnSameDay = dbHelper.isTakenOnSameDay(memberId, classDay);

        if(classTakenOnSameDay){
            if(isSameTime){
                Toast.makeText(this, "Time slot is already booked by you", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        else if(isMaxCap){
            Toast.makeText(this, "Class is at max capacity", Toast.LENGTH_SHORT).show();
            return;
        }


        else{
            EnrolledClass Ecls = dbHelper.addEnrolledClass(classStartHour, classStartMin, classEndHour, classEndMin, classId);

            //make association
            dbHelper.addEnrolledAssociation(memberId, Ecls.getId());

            //make toast
            Toast.makeText(this, "Enrolled in class", Toast.LENGTH_SHORT).show();
            //either finish or start new activity
            finish();
        }

    }
}