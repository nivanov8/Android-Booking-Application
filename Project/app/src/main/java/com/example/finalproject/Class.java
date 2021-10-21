package com.example.finalproject;

public class Class {
    private String name;
    private String description;
    private int id;

    public Class (int id, String name, String description){
        this.name = name;
        this.description = description;
        this.id = id;

    }

    //getters
    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getId(){return id;}

}
