package com.jvsc.fit.Dao.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jvsc.fit.Dao.Connect;
import com.jvsc.fit.Dao.GoalsDao;
import com.jvsc.fit.Entity.Goal;

@Repository
public class GoalJdbc implements GoalsDao{

	@Override
	public Goal findGoal(long id) throws SQLException{
		String sql = "SELECT * FROM goals WHERE id = ?";
		Connection con = Connect.open();
    	PreparedStatement ps = con.prepareStatement(sql.toUpperCase());
    	ps.setLong(1, id);
    	ResultSet rs = ps.executeQuery();
    	if(rs.next()) {
    		return mapGoal(rs);
    	}
		return null;
	}

	@Override
	public void addGoal(Goal goal) throws SQLException{
		String sql = "INSERT INTO goals (user_id, goal_type, goal_value, goal_period) VALUES (?, ?, ?, ?)";
		Connection con = Connect.open();
    	PreparedStatement ps = con.prepareStatement(sql.toUpperCase());
    	ps.setLong(1, goal.getUserId());
    	ps.setString(2, goal.getGoalType().name());
    	ps.setInt(3, goal.getGoalValue());
    	ps.setDate(4, goal.getGoalPeriod());
    	ps.execute();
	}

	@Override
	public List<Goal> listGoals() throws SQLException {
		List<Goal> goals = new ArrayList<Goal>();
		String sql = "SELECT * FROM goals";
		Connection con = Connect.open();
    	PreparedStatement ps = con.prepareStatement(sql.toUpperCase());
    	ResultSet rs = ps.executeQuery();
    	while(rs.next()) {
    		goals.add(mapGoal(rs));
    	}
		return goals;
	}

	@Override
	public void deleteGoal(long id) throws SQLException {
		String sql = "DELETE FROM goals WHERE id = ?";
		Connection con = Connect.open();
    	PreparedStatement ps = con.prepareStatement(sql.toUpperCase());
    	ps.setLong(1, id);
		ps.execute();
	}

	@Override
	public Goal updateGoal(Goal goal, long id) throws SQLException {
		String sql = "UPDATE goals SET user_id = ?, goal_type = ?, goal_value = ?, goal_period = ? WHERE id = ?";
		Connection con = Connect.open();
    	PreparedStatement ps = con.prepareStatement(sql.toUpperCase());
    	ps.setLong(1, goal.getUserId());
    	ps.setString(2, goal.getGoalType().name());
    	ps.setInt(3, goal.getGoalValue());
    	ps.setDate(4, goal.getGoalPeriod());
    	ps.setLong(5, id);
    	ps.execute();
    	
		return findGoal(id);
	}

	private Goal mapGoal(ResultSet rs) throws SQLException {
        Goal goal = new Goal();
        goal.setId(rs.getLong("id"));
        goal.setUserId(rs.getLong("user_id"));
        goal.setGoalType(rs.getString("goal_type"));
        goal.setGoalValue(rs.getInt("goal_value"));
        goal.setGoalPeriod(rs.getDate("goal_period"));
        return goal;
    }
}
