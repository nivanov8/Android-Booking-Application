package com.example.finalproject.baseadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject.R;

public class CreateClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_class);
    }

    public void onCreateClassClick(View view) { //throws exception
        EditText text1 = (EditText) findViewById(R.id.className);
        EditText text2 = (EditText) findViewById(R.id.classDescription);

        String name = text1.getText().toString().trim();
        String description = text2.getText().toString().trim();

        //if nothing entered do nothing
        if (name.length() == 0 || description.length() == 0 ){
            Toast.makeText(this, "Nothing Entered/Invalid Input", Toast.LENGTH_SHORT).show();
            return;
        }

        DataBaseHelper dbHelper = new DataBaseHelper(CreateClass.this);

        Class cls = dbHelper.addClass(name, description, -1, -1, null, null, -1);
        Toast.makeText(getApplicationContext(), "Class Added", Toast.LENGTH_SHORT).show();
        finish();
    }


}