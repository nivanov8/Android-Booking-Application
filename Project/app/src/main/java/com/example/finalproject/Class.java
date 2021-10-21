package com.example.finalproject;

public class Class {
    private String name;
    private String description;
    private String difficulty;
    private int size;

    public Class (String name, String description, String difficulty, int size){
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.size = size;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String getDifficulty(){
        return difficulty;
    }

    public int size(){
        return size;
    }

}
