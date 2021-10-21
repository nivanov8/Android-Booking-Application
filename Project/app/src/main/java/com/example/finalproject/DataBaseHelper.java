package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

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

    public static final String CLASS_TABLE = "CLASS_TABLE";
    public static final String CLASS_NAME = "CLASS_NAME";
    public static final String CLASS_DESCRIPTION = "CLASS_DESCRIPTION";
    public static final String CLASS_DIFFICULTY = "CLASS_DIFFICULTY";
    public static final String  CLASS_SIZE = "CLASS_SIZE";




    public DataBaseHelper(@Nullable Context context) {
        super(context, "users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createMembersTable = "CREATE TABLE " +  MEMBER_TABLE + " (" + MEMBER_ID + " INTEGER PRIMARY KEY, " + MEMBER_FIRSTNAME +
                " TEXT, " +  MEMBER_LASTNAME + " TEXT, " + MEMBER_USERNAME + " TEXT, " + MEMBER_PASSWORD +" TEXT, " +
                MEMBER_EMAIL + " TEXT)";

        String createInstructorsTable = "CREATE TABLE " + INSTRUCTOR_TABLE + " (" + INSTRUCTOR_ID + " INTEGER PRIMARY KEY, " + INSTRUCTOR_FIRSTNAME +
                " TEXT, " + INSTRUCTOR_LASTNAME + " TEXT, " + INSTRUCTOR_USERNAME + " TEXT, " + INSTRUCTOR_PASSWORD + " TEXT, " +
                INSTRUCTOR_EMAIL + " TEXT)";

        String createClassTable = "CREATE TABLE " + INSTRUCTOR_TABLE + " (" + INSTRUCTOR_ID + " INTEGER PRIMARY KEY, " + INSTRUCTOR_FIRSTNAME +
                " TEXT, " + INSTRUCTOR_LASTNAME + " TEXT, " + INSTRUCTOR_USERNAME + " TEXT, " + INSTRUCTOR_PASSWORD + " TEXT, " +
                INSTRUCTOR_EMAIL + " TEXT)";

        db.execSQL(createInstructorsTable);
        db.execSQL(createMembersTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    //method to add Member object to database
    public Member addMember(String firstname, String lastname, String username, String password, String email){
        // retrieve the database and create values to put into the database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        int id = getMaxId(INSTRUCTOR_TABLE, MEMBER_TABLE, INSTRUCTOR_ID, MEMBER_ID);
        //add attributes to values
        cv.put(MEMBER_ID, id);
        cv.put(MEMBER_FIRSTNAME, firstname);
        cv.put(MEMBER_LASTNAME, lastname);
        cv.put(MEMBER_USERNAME, username);
        cv.put(MEMBER_PASSWORD, password);
        cv.put(MEMBER_EMAIL, email);

        long insert = db.insert(MEMBER_TABLE, null, cv);

        System.out.println("Inserting with: " + id);
        Member member = new Member(id, firstname, lastname, username, password, email);
        return member;
    }

    //method to add instructor to database
    public Instructor addInstructor(String firstname, String lastname, String username, String password, String email){
        // retrieve the database and create values to put into the database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        int id = getMaxId(INSTRUCTOR_TABLE, MEMBER_TABLE, INSTRUCTOR_ID, MEMBER_ID);

        //add attributes to values
        cv.put(INSTRUCTOR_ID, id);
        cv.put(INSTRUCTOR_FIRSTNAME, firstname);
        cv.put(INSTRUCTOR_LASTNAME, lastname);
        cv.put(INSTRUCTOR_USERNAME, username);
        cv.put(INSTRUCTOR_PASSWORD, password);
        cv.put(INSTRUCTOR_EMAIL, email);

        long insert = db.insert(INSTRUCTOR_TABLE, null, cv);

        System.out.println("Inserting with: " + id);
        Instructor instructor = new Instructor(id, firstname, lastname, username, password, email);
        return  instructor;
    }

    //method to search for member in database
    public ArrayList<Member> findMembers(){
        ArrayList<Member> memberList =  new ArrayList<Member>();

        String queryString = "SELECT * FROM " + MEMBER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            // loop through results
            do{
                int memberId = cursor.getInt(0);
                String memberFirstName = cursor.getString(1);
                String memberLastName = cursor.getString(2);
                String memberUsername = cursor.getString(3);
                String memberPassword = cursor.getString(4);
                String memberEmail = cursor.getString(5);

                Member member = new Member(memberId, memberFirstName, memberLastName, memberUsername, memberPassword, memberEmail);
                memberList.add(member);

            }while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return memberList;
    }

    public Class addClass(String name, String description, String difficulty, int size){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CLASS_NAME, name);
        cv.put(CLASS_DESCRIPTION, description);
        cv.put(CLASS_DIFFICULTY, difficulty);
        cv.put(CLASS_SIZE, size);

        long insert = db.insert(CLASS_TABLE, null, cv);
        Class cls = new Class(name, description, difficulty, size);

        return cls;
    }

    public int getMaxId(String table1,String table2, String column1, String column2){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT MAX(" + column1 + ") FROM " + table1;
        String query2 = "SELECT MAX(" + column2 + ") FROM " + table2;
        Cursor cursor = db.rawQuery(query, null);
        int maxID1 = 0;
        while(cursor.moveToNext()) {
             maxID1 = cursor.getInt(0);
        }
        cursor.close();

        Cursor cursor2 = db.rawQuery(query2, null);
        int maxID2 = 0;
        while(cursor2.moveToNext()) {
            maxID2 = cursor2.getInt(0);
        }
        cursor2.close();

        System.out.println("id1 instructors " + maxID1);
        System.out.println("id2 members " + maxID2);


        int maxId = Math.max(maxID1, maxID2) + 1;

        System.out.println("idMax" + maxId);

        return maxId;
    }


    public User findUser(String username, String password){
        String query = "SELECT * FROM " + MEMBER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String fname = cursor.getString(1);
                String lname = cursor.getString(2);
                String uname = cursor.getString(3);
                String pword = cursor.getString(4);
                String email = cursor.getString(5);

                if (uname.equals(username) && pword.equals(password)){
                    Member mem = new Member(id, fname, lname, uname, pword, email);
                    return mem;
                }
            }while(cursor.moveToNext());
        }

        //query instructor table if not found in user table
        String query2 = "SELECT * FROM " + INSTRUCTOR_TABLE;
        Cursor cursor2 = db.rawQuery(query2, null);

        if (cursor2.moveToFirst()){
            do{
                int id = cursor2.getInt(0);
                String fname = cursor2.getString(1);
                String lname = cursor2.getString(2);
                String uname = cursor2.getString(3);
                String pword = cursor2.getString(4);
                String email = cursor2.getString(5);

                if (uname.equals(username) && pword.equals(password)){
                    Instructor inst = new Instructor(id, fname, lname, uname, pword, email);
                    return inst;
                }

            }while(cursor2.moveToNext());
        }
        return null;
    }
}
