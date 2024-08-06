package com.jvsc.Dao.DaoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.jvsc.Dao.Connect;
import com.jvsc.Dao.GoalsDao;
import com.jvsc.Entity.Goal;
import com.jvsc.Request.goal.AddGoalDto;
import com.jvsc.Request.goal.DeleteGoalDto;
import com.jvsc.Request.goal.RefreshDto;
import com.jvsc.Request.goal.UpdateGoalDto;
import com.jvsc.Response.FitException;
import com.jvsc.utils.Utils;

@Repository
public class GoalJdbc implements GoalsDao {

    @Override
    public Goal findGoal(long id) {
        String sql = "{call find_goal(?)}";
        try (Connection con = Connect.open();
             CallableStatement ps = con.prepareCall(sql)) {
            ps.setLong(1, id);
            ps.execute();
            ResultSet rs = ps.getResultSet();
                if (rs.next()) {
                    return mapGoal(rs);
                }
            
        } catch (SQLException e) {
            throw new FitException("Erro interno ao procurar o objetivo", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return null;
    }

    @Override
    public void addGoal(AddGoalDto goal) {
        String sql = "call add_goal(?, ?, ?, ?, ?)";
        try{
        var date = new Date(Utils.convertToDate(goal.getGoalPeriod()).getTime());
        
        
        var today = LocalDate.now();
        Connection con = Connect.open();
            CallableStatement ps = con.prepareCall(sql);
            
            ps.setLong(1, goal.getUserId());
            ps.setString(2, goal.getGoalType().name());
            ps.setInt(3, goal.getGoalValue());
            ps.setDate(4, date);
            ps.setDate(5, new Date(today.getYear(), today.getMonth().getValue(), today.getDayOfMonth()));
            ps.execute();
            
        }catch (Exception e){
            throw new FitException("Erro interno ao adicionar o objetivo", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        
    }

    @Override
    public List<Goal> listGoals() {
        String sql = "{call list_goals()}";
        List<Goal> goals = new ArrayList<>();
        try (Connection con = Connect.open();
             CallableStatement ps = con.prepareCall(sql);
             ) {
                 ps.execute();
                 ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                goals.add(mapGoal(rs));
            }
        } catch (SQLException e) {
            throw new FitException("Erro interno ao procurar os objetivos", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return goals;
    }

    public Goal refresh(RefreshDto ref){
        String sql = "call refreshgoal(?)";
        
        try (Connection con = Connect.open();
            CallableStatement ps = con.prepareCall(sql)) {
            ps.setLong(1, ref.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new FitException("Erro interno ao atualizar o objetivo", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        
        return findGoal(ref.getId());
    }

    @Override
    public void deleteGoal(DeleteGoalDto goal) {
        String sql = "call delete_goal(?)";
        try (Connection con = Connect.open();
             CallableStatement ps = con.prepareCall(sql)) {
            ps.setLong(1, goal.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new FitException("Erro interno ao deletar o objetivo", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public Goal updateGoal(UpdateGoalDto goal) {
        String sql = "call update_goal(?, ?, ?, ?)";
        try{
        var date = new Date(Utils.convertToDate(goal.getGoalPeriod()).getTime());
        try (Connection con = Connect.open();
             CallableStatement ps = con.prepareCall(sql)) {
            
            ps.setString(2, goal.getGoalType().name());
            ps.setInt(3, goal.getGoalValue());
            ps.setDate(4, date);
            ps.setLong(1, goal.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new FitException("Erro interno ao atualizar o objetivo", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        }catch (Exception e){
        throw new FitException("Erro interno ao atualizar o objetivo", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return findGoal(goal.getId());
    }

    private Goal mapGoal(ResultSet rs) {
        Goal goal = new Goal();
        try {
            goal.setId(rs.getLong("id"));
            goal.setUserId(rs.getLong("userid"));
            goal.setGoalType(rs.getString("goaltype"));
            goal.setGoalValue(rs.getInt("goalvalue"));
            goal.setGoalPeriod(rs.getDate("goalperiod"));
            goal.setStart(rs.getDate("start"));
        } catch (SQLException e) {
            throw new FitException("Erro interno ao mapear o objetivo", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return goal;
    }
}
