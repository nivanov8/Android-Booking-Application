package com.example.finalproject.member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.R;
import com.example.finalproject.baseadmin.Class;
import com.example.finalproject.baseadmin.DataBaseHelper;
import com.example.finalproject.baseadmin.EnrolledClass;

public class Member_UnenrollConfirmation extends AppCompatActivity {

    private int memberId;
    private int e_clsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_unenroll_confirmation);

        Intent intent = getIntent();
        memberId = intent.getIntExtra("memberId", -1);
        e_clsId = intent.getIntExtra("classId", -1);

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        EnrolledClass e_cls = dbHelper.getEnrolledClass(e_clsId);

        int startHour1 = e_cls.getStartHour();
        int startMin1 = e_cls.getStartMin();
        int endHour1 = e_cls.getEndHour();
        int endMin1 = e_cls.getEndMin();

        //add zeros where needed
        String startHour = startHour1 < 10 ? "0" + startHour1 : String.valueOf(startHour1);
        String startMin = startMin1 < 10 ? "0" + startMin1 : String.valueOf(startMin1);
        String endHour = endHour1 < 10 ? "0" + endHour1 : String.valueOf(endHour1);
        String endMin = endMin1 < 10 ? "0" + endMin1 : String.valueOf(endMin1);

        Class cls = dbHelper.findClass(e_cls.getClassOrgId());

        String className = cls.getName();
        String classDay = cls.getDay();

        //get the textviews
        TextView name = (TextView) findViewById(R.id.className2);
        TextView day = (TextView) findViewById(R.id.classDay2);
        TextView startTime = (TextView) findViewById(R.id.classStartTime1);
        TextView endTime = (TextView) findViewById(R.id.classEndTime1);

        //fix strings
        String start = startHour + ":" + startMin;
        String end = endHour + ":" + endMin;

        //add to textviews
        name.append(className);
        day.append(classDay);
        startTime.append(start);
        endTime.append(end);
    }

    public void onUnenrollConfirm(View view){
        DataBaseHelper dbHelper = new DataBaseHelper(this);

        dbHelper.removeEnrollAssociation(e_clsId);
        //remove e_cls comeplete
        dbHelper.removeEnrolledClass(e_clsId);

        Toast.makeText(this, "Unenrolled from class", Toast.LENGTH_SHORT).show();
        finish();
    }
}