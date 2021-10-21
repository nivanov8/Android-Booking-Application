package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteUser extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView userLV;
    ArrayList<User> users;
    ArrayList<Integer> userIds;
    ArrayList<String> userFNames;
    ArrayList<String> userLNames;
    ArrayList<String> userUsernames;
    ArrayList<String> userPasswords;
    ArrayList<String> userEmails;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        //create list view and arrays of information
        userLV = findViewById(R.id.userList);

        DataBaseHelper db = new DataBaseHelper(DeleteUser.this);
        users = db.getAllUsers();
        userIds = new ArrayList<Integer>();
        userFNames = new ArrayList<String>();
        userLNames = new ArrayList<String>();
        userUsernames = new ArrayList<String>();
        userPasswords = new ArrayList<String>();
        userEmails = new ArrayList<String>();


        //populate arrays with information
        int size = users.size();
        for (int i = 0; i < size; i++){
            userIds.add(users.get(i).getId());
            userFNames.add(users.get(i).getFirstname());
            userLNames.add(users.get(i).getLastname());
            userUsernames.add(users.get(i).getUsername());
            userPasswords.add(users.get(i).getPassword());
            userEmails.add(users.get(i).getEmail());
        }

        ArrayAdapter<String> userAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, userUsernames);
        userLV.setAdapter(userAdapter);

        userLV.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        int id = userIds.get(i);
        String type;
        if (users.get(i) instanceof Member){
            type = "member";
        }
        else if(users.get(i) instanceof Instructor){
            type = "instructor";
        }
        //NEED TO THINK OF SOMETHING HERE
        else{
            type = "member";
        }

        DataBaseHelper dbHelper = new DataBaseHelper(DeleteUser.this);
        dbHelper.deleteUser(id, type);

        Toast.makeText(this, "Deleted User", Toast.LENGTH_SHORT).show();
        finish();
        startActivity(getIntent());
    }
}