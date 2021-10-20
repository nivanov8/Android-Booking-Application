package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    //constants
    public static final String MEMBER_TABLE = "MEMBER_TABLE";
    public static final String MEMBER_FIRSTNAME = "MEMBER_FNAME";
    public static final String MEMBER_LASTNAME = "MEMBER_LNAME";
    public static final String MEMBER_USERNAME = "MEMBER_USERNAME";
    public static final String MEMBER_PASSWORD = "MEMBER_PASSWORD";
    public static final String MEMBER_EMAIL = "MEMBER_EMAIL";
    public static final String MEMBER_ID = "MEMBER_ID";

    public static final String INSTRUCTOR_TABLE = "INSTRUCTOR_TABLE";
    public static final String INSTRUCTOR_FIRSTNAME = "INSTRUCTOR_FNAME";
    public static final String INSTRUCTOR_LASTNAME = "INSTRUCTOR_LNAME";
    public static final String INSTRUCTOR_USERNAME = "INSTRUCTOR_USERNAME";
    public static final String INSTRUCTOR_PASSWORD = "INSTRUCTOR_PASSWORD";
    public static final String INSTRUCTOR_EMAIL = "INSTRUCTOR_EMAIL";
    public static final String INSTRUCTOR_ID = "INSTRUCTOR_ID";



    public DataBaseHelper(@Nullable Context context) {
        super(context, "users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table for members
        String createMemberTable = "CREATE TABLE " +  MEMBER_TABLE + " (" + MEMBER_ID + " INTEGER PRIMARY KEY, " + MEMBER_FIRSTNAME +
                " TEXT, " +  MEMBER_LASTNAME + " TEXT, " + MEMBER_USERNAME + " TEXT, " + MEMBER_PASSWORD +" TEXT, " +
                MEMBER_EMAIL + " TEXT)";

        String createInstructorTable = "CREATE TABLE " + INSTRUCTOR_TABLE + " (" + INSTRUCTOR_ID + " INTEGER PRIMARY KEY, " + INSTRUCTOR_FIRSTNAME +
                " TEXT, " + INSTRUCTOR_LASTNAME + " TEXT, " + INSTRUCTOR_USERNAME + " TEXT, " + INSTRUCTOR_PASSWORD + " TEXT, " +
                INSTRUCTOR_EMAIL + " TEXT)";

        db.execSQL(createMemberTable);
        db.execSQL(createInstructorTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + MEMBER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + INSTRUCTOR_TABLE);
        onCreate(db);
    }

    //method to add Member object to database
    public boolean addMember(Member member){
        // retrieve the database and create values to put into the database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        //add attributes to values
        cv.put(MEMBER_ID, member.getId());
        cv.put(MEMBER_FIRSTNAME, member.getFirstname());
        cv.put(MEMBER_LASTNAME, member.getLastname());
        cv.put(MEMBER_USERNAME, member.getUsername());
        cv.put(MEMBER_PASSWORD, member.getPassword());
        cv.put(MEMBER_EMAIL, member.getEmail());

        long insert = db.insert(MEMBER_TABLE, null, cv);

        if(insert == -1){
            return false;
        }
        else{
            return true;
        }
    }

    // add instructor to db method
    public boolean addInstructor(Instructor instructor){
        // retrieve the database and create values to put into the database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        //add attributes to values
        cv.put(INSTRUCTOR_ID, instructor.getId());
        cv.put(INSTRUCTOR_FIRSTNAME, instructor.getFirstname());
        cv.put(INSTRUCTOR_LASTNAME, instructor.getLastname());
        cv.put(INSTRUCTOR_USERNAME, instructor.getUsername());
        cv.put(INSTRUCTOR_PASSWORD, instructor.getPassword());
        cv.put(INSTRUCTOR_EMAIL, instructor.getEmail());

        long insert = db.insert(INSTRUCTOR_TABLE, null, cv);

        if (insert == -1){
            return false;
        }
        else{
            return true;
        }
    }
}
