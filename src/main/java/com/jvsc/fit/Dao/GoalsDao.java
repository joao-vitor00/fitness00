package com.jvsc.fit.Dao;

import java.util.List;

import com.jvsc.fit.Entity.Goal;


public interface GoalsDao {

    public Goal findGoal(long id);
    public void addGoal(Goal goal);
    public List<Goal> listGoals();
    public void deleteGoal(long id);
    public Goal updateGoal(Goal goal);

}
