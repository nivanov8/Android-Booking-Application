package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    //login button is clicked
    public void onLogin(View view){
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
    }

    //signup button is clicked
    public void onSignup(View view){
        Intent intent = new Intent(getApplicationContext(), Signup.class);
        startActivity(intent);
    }
}