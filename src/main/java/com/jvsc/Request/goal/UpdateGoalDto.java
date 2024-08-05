package com.jvsc.Request.goal;

import com.jvsc.enums.GoalType;



public class UpdateGoalDto {
    private long id;
	private GoalType goalType; 
	private int goalValue;
	private String goalPeriod;
	private boolean completed;
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    

    public GoalType getGoalType() {
        return goalType;
    }

    public void setGoalType(GoalType goalType) {
        this.goalType = goalType;
    }

    public int getGoalValue() {
        return goalValue;
    }

    public void setGoalValue(int goalValue) {
        this.goalValue = goalValue;
    }

    public String getGoalPeriod() {
        return goalPeriod;
    }

    public void setGoalPeriod(String goalPeriod) {
        this.goalPeriod = goalPeriod;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    
}
