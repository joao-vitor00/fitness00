package com.jvsc.fit.Dao;

import java.sql.SQLException;
import java.util.List;

import com.jvsc.fit.Entity.Goal;


public interface GoalsDao {

    public Goal findGoal(long id)throws SQLException;
    public void addGoal(Goal goal)throws SQLException;
    public List<Goal> listGoals()throws SQLException;
    public void deleteGoal(long id)throws SQLException;
    public Goal updateGoal(Goal goal, long id)throws SQLException;

}
