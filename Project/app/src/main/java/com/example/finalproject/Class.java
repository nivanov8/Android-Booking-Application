package com.example.finalproject;

public class Class {
    private String name;
    private String description;
    private int id;
    private String time;
    private String difficulty;
    private String day;
    private String capacity;


    public Class (int id, String name, String description){
        this.name = name;
        this.description = description;
        this.id = id;
        this.time = null;
        this.difficulty = null;
        this.day = null;
        this.capacity = null;
    }

    public Class(int id, String name, String description, String time, String difficulty, String day, String capacity){
        this.name = name;
        this.description = description;
        this.id = id;
        this.time = time;
        this.difficulty = difficulty;
        this.day = day;
        this.capacity = capacity;
    }

    //getters
    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getId(){return id;}

    public String getTime(){return time;}

    public String getCapacity(){return capacity;}
}
