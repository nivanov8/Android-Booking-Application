package com.example.finalproject.member;

import androidx.appcompat.app.AppCompatActivity;
import com.example.finalproject.R;
import com.example.finalproject.baseadmin.Class;
import com.example.finalproject.baseadmin.DataBaseHelper;
import com.example.finalproject.baseadmin.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Member_LoginPage extends AppCompatActivity {

    private String type;
    private String firstname;
    private String username;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_login_page);
        Intent intent = getIntent();
        type = intent.getStringExtra("type").toString();
        firstname = intent.getStringExtra("firstname").toString();
        username = intent.getStringExtra("username").toString();
        id = intent.getIntExtra("memberId", -1);

        TextView text = (TextView) findViewById(R.id.memName);
        //System.out.println(firstname);
        text.append(firstname);
    }


    public void onViewSchedClasses(View view){
        Intent intent = new Intent(getApplicationContext(), Member_ViewSchedClasses.class);
        intent.putExtra("type", type);
        intent.putExtra("firstname", firstname);
        intent.putExtra("username", username);
        intent.putExtra("memberId", id);
        startActivity(intent);
    }

    public void onEnroll(View view){
        Intent intent = new Intent(getApplicationContext(), Member_EnrollList.class);
        intent.putExtra("type", type);
        intent.putExtra("firstname", firstname);
        intent.putExtra("username", username);
        intent.putExtra("memberId", id);
        startActivity(intent);
    }

    public void onViewMyClasses(View view){
        Intent intent = new Intent(getApplicationContext(), Member_ViewMyClasses.class);
        intent.putExtra("type", type);
        intent.putExtra("firstname", firstname);
        intent.putExtra("username", username);
        intent.putExtra("memberId", id);
        startActivity(intent);
    }

    public void onUnenroll(View view){
        Intent intent = new Intent(getApplicationContext(), Member_UnenrollList.class);
        intent.putExtra("type", type);
        intent.putExtra("firstname", firstname);
        intent.putExtra("username", username);
        intent.putExtra("memberId", id);
        startActivity(intent);
    }

    public void onLogout(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}