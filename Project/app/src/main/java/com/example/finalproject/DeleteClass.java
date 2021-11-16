package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteClass extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView classLV;
    ArrayList<Class> classes;
    ArrayList<String> classNames;
    ArrayList<String> classDescriptions;
    ArrayList<Integer> classIds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_class);

        //create list view and arrays of information
        classLV = findViewById(R.id.classListView);

        DataBaseHelper db = new DataBaseHelper(DeleteClass.this);
        classes = db.getAllClasses();
        classNames = new ArrayList<String>();
        classDescriptions = new ArrayList<String>();
        classIds = new ArrayList<Integer>();

        //populate arrays with information
        int size = classes.size();
        for (int i = 0; i < size; i++){
            classNames.add(classes.get(i).getName());
            classDescriptions.add(classes.get(i).getDescription());
            classIds.add(classes.get(i).getId());
        }

        //create array adapter
        ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classNames);
        classLV.setAdapter(classAdapter);

        classLV.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String name = adapterView.getItemAtPosition(i).toString();

        int id = classIds.get(i);

        DataBaseHelper dbHelper = new DataBaseHelper(DeleteClass.this);
        dbHelper.deleteClass(id);
        Toast.makeText(this, "Deleted Class", Toast.LENGTH_SHORT).show();
        finish();
        startActivity(getIntent());
    }
}