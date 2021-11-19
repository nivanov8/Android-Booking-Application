package com.example.finalproject.baseadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.finalproject.R;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }

    public void onNext(View view){
        Intent intent = new Intent(getApplicationContext(), Signup2.class);

        //check that one checkbox was clicked
        CheckBox member = (CheckBox) findViewById(R.id.member);
        CheckBox instructor = (CheckBox) findViewById(R.id.instructor);

        //if both are checked
        if (member.isChecked() && instructor.isChecked()){
            Toast.makeText(this, "Check One", Toast.LENGTH_SHORT).show();
            return;  //do nothing
        }
        //none are checked
        else if (!member.isChecked() && !instructor.isChecked()){
            Toast.makeText(this, "Check One", Toast.LENGTH_SHORT).show();
            return;  //do nothing
        }
        //if we get here it means only 1 is checked
        else{
            //check if member is checked
            if (member.isChecked()){
                String type = member.getText().toString();
                intent.putExtra("type", type);
            }
            //check if instructor is checked
            else if(instructor.isChecked()){
                String type = instructor.getText().toString();
                intent.putExtra("type", type);
            }
            startActivity(intent);
        }
    }
}