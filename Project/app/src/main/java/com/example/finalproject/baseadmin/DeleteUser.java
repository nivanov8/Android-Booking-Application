package com.example.finalproject.baseadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.finalproject.R;

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
        setContentView(R.layout.delete_user);

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
            System.out.println(users.get(i).getFirstname());
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
        String type = null;
        if (users.get(i) instanceof Member){
            type = "member";
        }
        else if(users.get(i) instanceof Instructor){
            type = "instructor";
        }

        if(type.equals("instructor")){
            DataBaseHelper dbHelper = new DataBaseHelper(DeleteUser.this);
            dbHelper.deleteUser(id, type);
            //delete all those classes he tuaght
            ArrayList<Class> classes = dbHelper.findTaughtClasses(id);
            int size = classes.size();
            for (int j = 0; j<size; j++){
                int clsId = classes.get(i).getId();
                dbHelper.deleteClass(clsId);
            }
            //delete association
            dbHelper.deleteTeaches(id);
        }
        else if (type.equals("member")){
            DataBaseHelper dbHelper = new DataBaseHelper(this);
            dbHelper.deleteUser(id, type);
            //delete class enrolled associations and classes enrolled

            ArrayList<EnrolledClass> e_classes = dbHelper.findAllEnrolledClasses(id);
            int size = e_classes.size();
            for(int j = 0; j<size; j++){
                int e_clsId = e_classes.get(j).getId();
                dbHelper.removeEnrolledClass(e_clsId);
            }
            dbHelper.deleteEnrolledAssociation(id);
        }

        Toast.makeText(this, "Deleted User", Toast.LENGTH_SHORT).show();
        finish();
        startActivity(getIntent());
    }
}