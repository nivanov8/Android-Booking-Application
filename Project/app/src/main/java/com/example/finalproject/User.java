package com.example.finalproject;

public abstract class User {
    protected String firstname;
    protected String lastname;
    protected String username;
    protected String password;
    protected String email;
    //int id;

    public User(String firstname, String lastname, String username, String password, String email){
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
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
}
