package com.example.finalproject.baseadmin;

public class EnrolledClass {
    private int classOrgId;
    private int endHour;
    private int endMin;
    private int id;
    private int startHour;
    private int startMin;

    public EnrolledClass(int id, int startHour, int startMin, int endHour, int endMin, int classOrgId){
        this.id = id;
        this.classOrgId = classOrgId;
        this.endHour = endHour;
        this.endMin = endMin;
        this.startHour = startHour;
        this.startMin = startMin;
    }

    public int getClassOrgId(){return classOrgId;}
    public int getEndHour(){return endHour;}
    public int getEndMin(){return endMin;}
    public int getId(){return id;}
    public int getStartHour(){return startHour;}
    public int getStartMin(){return startMin;}
}
