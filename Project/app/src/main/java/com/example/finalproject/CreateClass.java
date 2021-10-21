package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);
    }

    public void onCreateClassClick(View view) { //throws exception
        EditText text1 = (EditText) findViewById(R.id.className);
        EditText text2 = (EditText) findViewById(R.id.classDescription);

        String name = text1.getText().toString().trim();
        String description = text2.getText().toString().trim();

        //if nothing entered do nothing
        if (name.length() == 0 || description.length() == 0 ){
            return;
        }

        DataBaseHelper dbHelper = new DataBaseHelper(CreateClass.this);

        Class cls = dbHelper.addClass(name, description);
        Toast.makeText(getApplicationContext(), "Class Added", Toast.LENGTH_SHORT).show();
        finish();
    }


}