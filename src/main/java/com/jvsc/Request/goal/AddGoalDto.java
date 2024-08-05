package com.jvsc.Request.goal;

import java.util.Date;

import com.jvsc.enums.GoalType;

public class AddGoalDto {
 

    private Long userId;
    private GoalType goalType;
    private int goalValue;
    private String goalPeriod;
    
    
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
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
    
}
