package com.jvsc.Request.activity;



public class UpdateActivityDto {
    private long Id;
	private String type;
	private int duration;
	private int calories;
	private String date;
	private boolean completed;
    
    

    public UpdateActivityDto() {
    }

    public UpdateActivityDto( String type, int duration, int calories, String date, boolean completed,  long Id) {
        this.Id = Id;
        
        
        this.type = type;
        this.duration = duration;
        this.calories = calories;
        this.date = date;
        this.completed = completed;
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
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }
}
