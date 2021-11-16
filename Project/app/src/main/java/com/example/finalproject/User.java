
package com.example.finalproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public abstract class User {
    protected String firstname;
    protected String lastname;
    protected String username;
    protected String password;
    protected String email;
    protected int userId;

    public User(int userId, String firstname, String lastname, String username, String password, String email){
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userId = userId;
    }

    //getters and setters
    public String getFirstname(){
        return firstname;
    }

    public String getLastname(){
        return lastname;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getEmail(){
        return email;
    }

    public int getId() {return userId;}
}
