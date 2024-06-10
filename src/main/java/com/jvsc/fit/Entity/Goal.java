package com.jvsc.fit.Entity;

import java.sql.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Goal {
	
	public Goal() {
		this.completed = false;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Long userId;
    private GoalType goalType; // Ex: "calorias", "duracao"
    private int goalValue;
    private Date goalPeriod;
    private boolean completed;
	
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
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
	public GoalType getGoalType() {
		return goalType;
	}
	public void setGoalType(String goalType) {
		
		if(goalType == GoalType.CALORIAS.name())
			this.goalType = GoalType.CALORIAS;
		else
			this.goalType = GoalType.DURACAO;
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
	
}
