package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        Intent intent = getIntent();

        String userType = intent.getStringExtra("type").toString();
        String username = intent.getStringExtra("username").toString();
        String firstName = intent.getStringExtra("firstName").toString();

        TextView text1 = (TextView) findViewById(R.id.type);
        TextView text2 = (TextView) findViewById(R.id.uname);
        TextView text3 = (TextView) findViewById(R.id.fname);

        text1.append(userType);
        text2.append(username);
        text3.append(firstName);
    }


    public void onLogout(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}