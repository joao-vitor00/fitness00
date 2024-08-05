package com.jvsc.Entity;

import java.sql.Date;

import com.jvsc.enums.GoalType;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "goals")
public class Goal {
	
	public Goal() {
		this.completed = false;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column
	private Long userId;
    
	@Column
	private GoalType goalType; 
    
	@Column
	private int goalValue;
    
	@Column
	private Date goalPeriod;
    
	@Column
	private boolean completed;

	@Column
	private Date start;

	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
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
