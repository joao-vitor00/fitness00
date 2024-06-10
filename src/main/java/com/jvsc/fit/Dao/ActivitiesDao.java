package com.jvsc.fit.Dao;

import java.sql.SQLException;
import java.util.List;

import com.jvsc.fit.Entity.Activity;

public interface ActivitiesDao {
	public void addActivity(Activity activity)throws SQLException;
	public void deleteActivity(long id)throws SQLException;
	public Activity findActivity(long id)throws SQLException;
	public List<Activity> listActivities()throws SQLException;
	public Activity updateActivity(Activity activity, long id)throws SQLException;
}
