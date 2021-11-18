package com.example.finalproject;

public class Class {
    private String name;
    private String description;
    private int id;
    private int hour;
    private int min;
    private String difficulty;
    private String day;
    private int capacity;


    public Class (int id, String name, String description){
        this.name = name;
        this.description = description;
        this.id = id;
        this.difficulty = null;
        this.hour = -1;
        this.min = -1;
        this.day = null;
        this.capacity = -1;
    }

    public Class(int id, String name, String description, int hour, int min, String difficulty, String day, int capacity){
        this.name = name;
        this.description = description;
        this.id = id;
        this.hour = hour;
        this.min = min;
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

    public int getCapacity(){return capacity;}
}
