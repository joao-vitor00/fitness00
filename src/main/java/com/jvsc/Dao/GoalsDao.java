package com.jvsc.Dao;

import java.sql.SQLException;
import java.util.List;

import com.jvsc.Entity.Goal;
import com.jvsc.Request.goal.AddGoalDto;
import com.jvsc.Request.goal.DeleteGoalDto;
import com.jvsc.Request.goal.UpdateGoalDto;


public interface GoalsDao {

    public Goal findGoal(long id);
    public void addGoal(AddGoalDto goal);
    public List<Goal> listGoals();
    public void deleteGoal(DeleteGoalDto goal);
    public Goal updateGoal(UpdateGoalDto goal);

}
