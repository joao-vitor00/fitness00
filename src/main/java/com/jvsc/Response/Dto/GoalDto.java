package com.jvsc.Response.Dto;

import java.util.Date;

import com.jvsc.enums.GoalType;

public class GoalDto {
    
    private long id;
    private Long userId;
    private String userName;
    private GoalType goalType;
    private int goalValue;
    private Date goalPeriod;
    private boolean completed;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Date getGoalPeriod() {
        return goalPeriod;
    }

    public void setGoalPeriod(Date goalPeriod) {
        this.goalPeriod = goalPeriod;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public GoalDto(long id, Long userId, String userName, GoalType goalType, int goalValue, Date goalPeriod, boolean completed) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.goalType = goalType;
        this.goalValue = goalValue;
        this.goalPeriod = goalPeriod;
        this.completed = completed;
    }
    
    public GoalDto() {
    }
    
    
}
