package com.example.finalproject.baseadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.R;

public class ChangeClassInfo extends AppCompatActivity {

    String currectClassName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_class_info);

        TextView name = (TextView) findViewById(R.id.classNametext);
        TextView desc = (TextView) findViewById(R.id.classdescriptionText);

        Intent intent = getIntent();

        currectClassName = intent.getStringExtra("name").toString();
        String currentClassDesc = intent.getStringExtra("description").toString();

        name.append(currectClassName);
        desc.append(currentClassDesc);
    }

    //update the class
    public void onFinishChanges(View view){
        Intent intent = getIntent();
        int classId = intent.getIntExtra("id", 0);

        EditText text1 = (EditText) findViewById(R.id.editTextClassName);
        EditText text2 = (EditText) findViewById(R.id.editTextClassDesc);

        String newName = text1.getText().toString().trim();
        String newDesc = text2.getText().toString().trim();

        //if nothing is entered do nothing
        if (newName.length() == 0 || newDesc.length() == 0){
            Toast.makeText(this, "Nothing Entered/Invalid Input", Toast.LENGTH_SHORT).show();
            return;
        }

        DataBaseHelper dbHelper = new DataBaseHelper(ChangeClassInfo.this);
        dbHelper.updateClass(classId, newName, newDesc);
        dbHelper.adminUpdateClass(currectClassName, newName, newDesc);

        Intent intent2 = new Intent(getApplicationContext(), EditClass.class);
        Toast.makeText(this, "Class updated", Toast.LENGTH_SHORT).show();
        finish();
        startActivity(intent2);
    }
}