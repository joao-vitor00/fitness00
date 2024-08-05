package com.jvsc.Dao;

import java.sql.SQLException;
import java.util.List;
import com.jvsc.Request.activity.AddActivityDto;
import com.jvsc.Request.activity.DeleteActivityDto;
import com.jvsc.Request.activity.UpdateActivityDto;
import com.jvsc.Entity.Activity;

public interface ActivitiesDao {
	public void addActivity(AddActivityDto activity);
	public void deleteActivity(long id);
	public Activity findActivity(long id);
	public List<Activity> listActivities();
	public Activity updateActivity(UpdateActivityDto activity);
}
