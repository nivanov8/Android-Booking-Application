package com.example.finalproject.baseadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.finalproject.R;

public class AdminPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_page);
    }

    public void onCreateClass(View view){
        Intent intent = new Intent(getApplicationContext(), CreateClass.class);
        startActivity(intent);
    }

    public void onDeleteClass(View view){
        Intent intent = new Intent(getApplicationContext(), DeleteClass.class);
        startActivity(intent);
    }

    public void onEditClass(View view){
        Intent intent = new Intent(getApplicationContext(), EditClass.class);
        startActivity(intent);
    }

    public void onDelUser(View view){
        Intent intent = new Intent(getApplicationContext(), DeleteUser.class);
        startActivity(intent);
    }
}