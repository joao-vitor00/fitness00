package com.jvsc.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvsc.fit.Dao.DaoImpl.GoalJdbc;
import com.jvsc.fit.Entity.Goal;

@Service
public class GoalService {

    @Autowired
    GoalJdbc goalJdbc;
    
    public void addGoal(Goal goal) {
        try {
            goalJdbc.addGoal(goal);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGoal(long id) {
        try {
            goalJdbc.deleteGoal(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Goal updateGoal(Goal goal, long id) {
        try {
           return goalJdbc.updateGoal(goal, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Goal findGoal(long id) {
        try {
            return goalJdbc.findGoal(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Goal> listGoals() {
        try {
            return goalJdbc.listGoals();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Goal> listCompletedGoals() {
        try {
            List<Goal> list = new ArrayList<>();
            for (Goal g : goalJdbc.listGoals()) {
                if (g.isCompleted()) {
                    list.add(g);
                }
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
