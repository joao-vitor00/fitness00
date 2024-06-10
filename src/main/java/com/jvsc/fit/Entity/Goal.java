package com.jvsc.fit.Entity;

import java.sql.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Goal {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Long userId;
    private GoalType goalType; // Ex: "calorias", "duracao"
    private int goalValue;
    private Date goalPeriod;
}
