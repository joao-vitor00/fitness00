package com.jvsc.fit.Entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Activity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long userId;
	private String type;
	private int duration;
	private int calories;
	private Date date;
	private boolean completed;
	

}
