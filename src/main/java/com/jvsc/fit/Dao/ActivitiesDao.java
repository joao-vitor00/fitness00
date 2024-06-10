package com.jvsc.fit.Dao;

import java.util.List;

import com.jvsc.fit.Entity.Activity;

public interface ActivitiesDao {
	public void addActivity(Activity activity);
	public void deleteActivity(long id);
	public Activity findActivity(long id);
	public List<Activity> listActivities();
	public Activity updateActivity(Activity activity);
}
