package com.example.finalproject.instructor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.finalproject.baseadmin.Class;
import com.example.finalproject.baseadmin.DataBaseHelper;
import com.example.finalproject.R;

import java.util.ArrayList;

public class Instructor_ViewAllScheduled extends AppCompatActivity {

    private ListView classLV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insutrctor_view_all_scheduled);

        classLV = findViewById(R.id.List);
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        ArrayList<Class> classList = dbHelper.getAllScheduledClasses();

        ArrayList<String> classNames = new ArrayList<String>();

        int size = classList.size();
        for (int i = 0; i < size; i++){
            Class cls = classList.get(i);
            classNames.add(cls.getName() + " (" + cls.getDay() + ")");
        }

        ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classNames);
        classLV.setAdapter(classAdapter);
    }

    public void onSearch(View view){
        EditText searchInput = (EditText) findViewById(R.id.searchInput);
        String input = searchInput.getText().toString().trim();

        if (input.equals("")){
            Toast.makeText(this, "Enter class name or instructor name to search", Toast.LENGTH_SHORT).show();
            return;
        }

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        ArrayList<Class> list = dbHelper.findScheduledClass(input);

        ArrayList<String> names = new ArrayList<String>();

        int size = list.size();
        for(int i=0;i<size;i++){
            Class cls = list.get(i);
            names.add(cls.getName() + " (" + cls.getDay() + ")");
        }
        ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        classLV.setAdapter(classAdapter);
    }

    public void onViewAllSched(View view){
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        ArrayList<Class> classList = dbHelper.getAllScheduledClasses();

        ArrayList<String> classNames = new ArrayList<String>();

        int size = classList.size();
        for (int i = 0; i < size; i++){
            Class cls = classList.get(i);
            classNames.add(cls.getName() + " (" + cls.getDay() + ")");
        }

        ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classNames);
        classLV.setAdapter(classAdapter);
    }
}