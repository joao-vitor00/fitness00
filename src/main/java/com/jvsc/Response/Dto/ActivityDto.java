package com.jvsc.Response.Dto;

import java.util.Date;

public class ActivityDto {
    
    private long id;
    private long userId;
    private String userName;
    private String type;
    private int duration;
    private int calories;
    private Date date;
    private boolean completed;
    
    public ActivityDto(long id, long userId, String userName, String type, int duration, int calories, Date date,
            boolean completed) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.type = type;
        this.duration = duration;
        this.calories = calories;
        this.date = date;
        this.completed = completed;
    }

    public ActivityDto() { }
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public int getCalories() {
        return calories;
    }
    public void setCalories(int calories) {
        this.calories = calories;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
   

}
