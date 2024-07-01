package com.jvsc.fit.Dao.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jvsc.fit.Dao.ActivitiesDao;
import com.jvsc.fit.Dao.Connect;
import com.jvsc.fit.Entity.Activity;

@Repository
public class ActivityJdbc implements ActivitiesDao {

	@Override
	public void addActivity(Activity activity) throws SQLException {
		String sql = "INSERT INTO activities (user_id, activity_type, duration, calories, activity_date, completed) VALUES (?, ?, ?, ?, ?, ?)";
		Connection con = Connect.open();
    	PreparedStatement ps = con.prepareStatement(sql.toUpperCase());
    	ps.setLong(1, activity.getUserId());
    	ps.setString(2, activity.getType());
    	ps.setInt(3, activity.getDuration());
    	ps.setInt(4, activity.getCalories());
    	ps.setString(5, activity.getDate().toString());
    	ps.setBoolean(6, activity.isCompleted());
	}

	@Override
	public void deleteActivity(long id) throws SQLException {
		String sql = "DELETE FROM activities WHERE id = ?";
		Connection con = Connect.open();
    	PreparedStatement ps = con.prepareStatement(sql.toUpperCase());
		ps.setLong(1, id);
		ps.execute();
	}

	@Override
	public Activity findActivity(long id) throws SQLException {
		String sql = "SELECT * FROM activities WHERE id = ?";
		Connection con = Connect.open();
    	PreparedStatement ps = con.prepareStatement(sql.toUpperCase());
    	ps.setLong(1, id);
    	ResultSet rs = ps.executeQuery();
    	if(rs.next()) {
    		return mapActivity(rs);
    	}
		return null;
	}

	@Override
	public List<Activity> listActivities() throws SQLException {
		String sql = "SELECT * FROM activities";
        List<Activity> act = new ArrayList<>();
        Connection con = Connect.open();
    	PreparedStatement ps = con.prepareStatement(sql.toUpperCase());
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
        	act.add(mapActivity(rs));
        }
        
        return act;
	}

	@Override
	public Activity updateActivity(Activity activity, long id) throws SQLException {
		String sql = "UPDATE activities SET activity_type = ?, duration = ?, calories = ?, activity_date = ?, completed = ? WHERE id = ?";
		
		Connection con = Connect.open();
    	PreparedStatement ps = con.prepareStatement(sql.toUpperCase());
    	ps.setString(1, activity.getType());
    	ps.setInt(2, activity.getDuration());
    	ps.setInt(3, activity.getCalories());
    	ps.setString(4, activity.getDate().toString());
    	ps.setBoolean(5, activity.isCompleted());
    	ps.setLong(6, id);
    	ps.execute();
    	
		return findActivity(id);
	}
	
	 private Activity mapActivity(ResultSet rs) throws SQLException {
	        Activity activity = new Activity();
	        activity.setId(rs.getLong("id"));
	        activity.setUserId(rs.getLong("user_id"));
	        activity.setType(rs.getString("activity_type"));
	        activity.setDuration(rs.getInt("duration"));
	        activity.setCalories(rs.getInt("calories"));
	        activity.setDate(rs.getDate("activity_date"));
	        activity.setCompleted(rs.getBoolean("completed"));
	        return activity;
	    }

}
