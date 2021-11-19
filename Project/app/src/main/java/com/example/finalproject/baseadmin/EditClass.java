package com.example.finalproject.baseadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.finalproject.R;

import java.util.ArrayList;

public class EditClass extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView classLV;
    ArrayList<Class> classes;
    ArrayList<String> classNames;
    ArrayList<String> classDescriptions;
    ArrayList<Integer> classIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_class);

        //create list view and arrays of information
        classLV = findViewById(R.id.editClass);

        DataBaseHelper db = new DataBaseHelper(EditClass.this);
        classes = db.getAllClasses();
        classNames = new ArrayList<String>();
        classDescriptions = new ArrayList<String>();
        classIds = new ArrayList<Integer>();

        //populate arrays with information
        int size = classes.size();
        System.out.println(size);
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
        String description = classDescriptions.get(i);

        Intent intent = new Intent(getApplicationContext(), ChangeClassInfo.class);
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        intent.putExtra("description", description);

        finish();
        startActivity(intent);
    }
}