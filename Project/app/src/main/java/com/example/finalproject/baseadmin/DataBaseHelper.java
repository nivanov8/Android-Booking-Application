package com.example.finalproject.baseadmin;

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

    public static final String CLASS_ID = "CLASS_ID";
    public static final String CLASS_TABLE = "CLASS_TABLE";
    public static final String CLASS_NAME = "CLASS_NAME";
    public static final String CLASS_DESCRIPTION = "CLASS_DESCRIPTION";
    public static final String CLASS_HOUR = "CLASS_HOUR";
    public static final String CLASS_MIN = "CLASS_MIN";
    public static final String CLASS_DIFFICULTY = "CLASS_DIFFICULTY";
    public static final String CLASS_DAY = "CLASS_DAY";
    public static final String CLASS_CAPACITY = "CLASS_CAPACITY";

    public static final String TEACHES_TABLE = "TEACHES_TABLE";


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

        String createClassTable = "CREATE TABLE " + CLASS_TABLE + " (" + CLASS_ID + " INTEGER PRIMARY KEY, " + CLASS_NAME +
                " TEXT, " + CLASS_DESCRIPTION + " TEXT, " + CLASS_HOUR + " TEXT, " + CLASS_MIN + " TEXT, " +
                CLASS_DIFFICULTY + " TEXT, " + CLASS_DAY + " TEXT," + CLASS_CAPACITY + " TEXT)";

        String createTeachesTables = "CREATE TABLE " + TEACHES_TABLE + " (" + INSTRUCTOR_ID + " INTEGER, " + CLASS_ID +
                " INTEGER, " + "FOREIGN KEY (" + INSTRUCTOR_ID + ") REFERENCES " + INSTRUCTOR_TABLE + "(" + INSTRUCTOR_ID +
                "), FOREIGN KEY (" + CLASS_ID + ") REFERENCES " + CLASS_TABLE + "(" + CLASS_ID + "))";


        db.execSQL(createInstructorsTable);
        db.execSQL(createMembersTable);
        db.execSQL(createClassTable);
        db.execSQL(createTeachesTables);
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

        Instructor instructor = new Instructor(id, firstname, lastname, username, password, email);
        return  instructor;
    }

    //add class to DB
    public Class addClass(String name, String description, int hour, int min, String difficulty, String day, int capacity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        int id = getMaxClassId(CLASS_TABLE, CLASS_ID);
        cv.put(CLASS_ID, id);
        cv.put(CLASS_NAME, name);
        cv.put(CLASS_DESCRIPTION, description);
        cv.put(CLASS_HOUR, hour);
        cv.put(CLASS_MIN, min);
        cv.put(CLASS_DIFFICULTY, difficulty);
        cv.put(CLASS_DAY, day);
        cv.put(CLASS_CAPACITY, capacity);


        long insert = db.insert(CLASS_TABLE, null, cv);
        Class cls = new Class(id, name, description);

        return cls;
    }

    //get max id for users
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

        int maxId = Math.max(maxID1, maxID2) + 1;
        return maxId;
    }

    //get maxID for class
    public int getMaxClassId(String table, String column){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT MAX(" + column + ") FROM " + table;
        Cursor cursor = db.rawQuery(query, null);

        int maxId = 0;
        while(cursor.moveToNext()){
            maxId = cursor.getInt(0);
        }
        cursor.close();
        return maxId + 1;
    }

    // returns the found user
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

    //return a list of all classes
    public ArrayList<Class> getAllClasses(){
        ArrayList<Class> list = new ArrayList<Class>();
        String query = "SELECT * FROM " + CLASS_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                int hour = cursor.getInt(3);
                int min = cursor.getInt(4);
                String difficulty = cursor.getString(5);
                String day = cursor.getString(6);
                int capacity = cursor.getInt(7);
                Class cls = new Class(id, name, description, hour, min, difficulty, day, capacity);
                list.add(cls);
            }while(cursor.moveToNext());
        }
        return list;
    }

    //delete a class
    public boolean deleteClass(int classId){
        String query = "DELETE FROM " + CLASS_TABLE + " WHERE " + CLASS_ID + " = " + classId;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }

    //delete a user
    public boolean deleteUser(int id, String type){
        String userType;
        String userId;
        if (type.equals("member")){
            userType = MEMBER_TABLE;
            userId = MEMBER_ID;
        }
        else if (type.equals("instructor")){
            userType = INSTRUCTOR_TABLE;
            userId = INSTRUCTOR_ID;
        }
        else{
            userType = MEMBER_TABLE;
            userId = MEMBER_ID;
        }
        String query = "DELETE FROM " + userType + " WHERE " + userId + " = " + id;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }

    //return a list of users
    public ArrayList<User> getAllUsers(){
        ArrayList<User> list = new ArrayList<User>();
        String query = "SELECT * FROM " + MEMBER_TABLE;
        String query2 = "SELECT * FROM " + INSTRUCTOR_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        Cursor cursor2 = db.rawQuery(query2, null);

        //fill list with members first
        if(cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String fname = cursor.getString(1);
                String lname = cursor.getString(2);
                String uname = cursor.getString(3);
                String pword = cursor.getString(4);
                String email = cursor.getString(5);

                Member member = new Member(id, fname, lname, uname, pword, email);
                list.add(member);
            } while (cursor.moveToNext());
        }

        if(cursor2.moveToFirst()) {
            do {
                int id = cursor2.getInt(0);
                System.out.println(id);
                String fname = cursor2.getString(1);
                String lname = cursor2.getString(2);
                String uname = cursor2.getString(3);
                String pword = cursor2.getString(4);
                String email = cursor2.getString(5);

                Instructor inst = new Instructor(id, fname, lname, uname, pword, email);
                list.add(inst);
            } while (cursor2.moveToNext());
        }
        return list;
    }

    //update a class
    public void updateClass(int id, String name, String description){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CLASS_NAME, name);
        cv.put(CLASS_DESCRIPTION, description);
        db.update(CLASS_TABLE, cv, "CLASS_ID=?", new String[]{String.valueOf(id)} );
    }

    public void updateTeachClass(int id, String difficulty, String day, int hour, int min, int capacity){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CLASS_DIFFICULTY, difficulty);
        cv.put(CLASS_DAY, day);
        cv.put(CLASS_HOUR, hour);
        cv.put(CLASS_MIN, min);
        cv.put(CLASS_CAPACITY, capacity);
        db.update(CLASS_TABLE, cv, "CLASS_ID=?", new String[]{String.valueOf(id)});
    }

    public void addTeacher(int instructorId, int classId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(INSTRUCTOR_ID, instructorId);
        cv.put(CLASS_ID, classId);

        db.insert(TEACHES_TABLE, null, cv);
    }

    public Class findClass(int classId){
        String query = "SELECT * FROM " + CLASS_TABLE + " WHERE " + CLASS_ID + "=" + "'" +
                classId + "'";

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            int hour = cursor.getInt(3);
            int min = cursor.getInt(4);
            String difficulty = cursor.getString(5);
            String day = cursor.getString(6);
            int capacity = cursor.getInt(7);

            Class cls = new Class(classId, name, description, hour, min, difficulty, day, capacity);
            return cls;
        }
        return null;

    }

    public ArrayList<Class> findTaughtClasses(int instructorId){
        ArrayList<Class> list = new ArrayList<Class>();

        String query = "SELECT * FROM " + TEACHES_TABLE + " WHERE " + INSTRUCTOR_ID + "=" + "'" +
                instructorId + "'";

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int classId = cursor.getInt(1);
                Class cls = findClass(classId);
                list.add(cls);

            } while (cursor.moveToNext());
        }

        return list;
    }

    public boolean isTaught(int classId){
        String query = "SELECT * FROM " +  TEACHES_TABLE + " WHERE " + CLASS_ID + "=" +
                "'" + classId + "'";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            return true;
        }
        return false;
    }

    public boolean duplicateUsername(String username){
        String query = "SELECT * FROM " + MEMBER_TABLE + " WHERE " + MEMBER_USERNAME +
                "=" + "'" + username + "'";
        String query2 = "SELECT * FROM " + INSTRUCTOR_TABLE + " WHERE " + INSTRUCTOR_USERNAME +
                "=" + "'" + username + "'";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Cursor cursor2 = db.rawQuery(query2, null);

        if (cursor.moveToFirst()){
            return true;
        }
        else if (cursor2.moveToFirst()){
            return true;
        }
        return false;
    }

    public Instructor findInstructor(int id){
        String query = "SELECT * FROM " + INSTRUCTOR_TABLE + " WHERE " + INSTRUCTOR_ID + "=" + "'" +
                id + "'";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            int instId = cursor.getInt(0);
            String instFname = cursor.getString(1);
            String instLname = cursor.getString(2);
            String instUname = cursor.getString(3);
            String instPword = cursor.getString(4);
            String instEmail = cursor.getString(5);

            Instructor inst = new Instructor(instId, instFname, instLname, instUname, instPword, instEmail);
            return inst;
        }
        return null;

    }

    public ArrayList<Class> getAllScheduledClasses(){
        String query = "SELECT * FROM " + TEACHES_TABLE;

        ArrayList<Class> classList = new ArrayList<Class>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do{
                int classId = cursor.getInt(1);
                Class cls = findClass(classId);
                classList.add(cls);
            }while(cursor.moveToNext());
        }
        return classList;
    }

    public ArrayList<Instructor> getAllScheduledInstrcutors(){
        String query = "SELECT * FROM " + TEACHES_TABLE;

        ArrayList<Instructor> instList= new ArrayList<Instructor>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToNext()){
            do{
                int instId = cursor.getInt(0);
                Instructor inst = findInstructor(instId);
                instList.add(inst);
            }while(cursor.moveToNext());
        }
        return instList;
    }

    public ArrayList<Class> findScheduledClass(String input){
        ArrayList<Class> classList = getAllScheduledClasses();
        ArrayList<Instructor> instList = getAllScheduledInstrcutors();

        ArrayList<Class> returnList = new ArrayList<Class>();

        int clsSize = classList.size();
        for (int i=0;i<clsSize;i++){
            if (input.equals(classList.get(i).getName())){
                returnList.add(classList.get(i));
            }
        }

        int instSize = instList.size();
        for(int i =0;i<instSize;i++){
            if(input.equals(instList.get(i).getFirstname())){
                returnList.add(classList.get(i));
            }
        }
        return returnList;
    }

    public boolean classIsTaken(String day, String name){
        ArrayList<Class> classList = getAllScheduledClasses();

        int size = classList.size();
        for (int i =0; i<size;i++){
            Class cls = classList.get(i);
            //System.out.println(cls.getName());
            if ((cls.getDay().equals(day)) && (cls.getName().equals(name))){
                return true;
            }
        }
        return false;
    }

    public void deleteTaughtClass(int classId){
        //String query = "DELETE FROM " + TEACHES_TABLE + " WHERE " + CLASS_ID + " = " + classId;

        SQLiteDatabase db = getWritableDatabase();
        //db.rawQuery(query, null);
        db.delete(TEACHES_TABLE, "CLASS_ID=?", new String[]{String.valueOf(classId)});
    }

    public void deleteTeaches(int instId){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TEACHES_TABLE, "INSTRUCTOR_ID=?", new String[]{String.valueOf(instId)});
    }

    public void deleteClassAssociation(String name){
        ArrayList<Class> list = findScheduledClass(name);
        SQLiteDatabase db = getWritableDatabase();
        int size = list.size();
        for (int i = 0; i <size; i++){
            int clsId = list.get(i).getId();
            deleteClass(clsId);
            db.delete(TEACHES_TABLE, "CLASS_ID=?", new String[]{String.valueOf(clsId)});
        }
    }
}
