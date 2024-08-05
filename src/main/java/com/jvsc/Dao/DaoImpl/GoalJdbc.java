package com.jvsc.Dao.DaoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String sql = "SELECT * FROM goals WHERE id = ?";
        try (Connection con = Connect.open();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapGoal(rs);
                }
            }
        } catch (SQLException e) {
            throw new FitException("Erro interno ao procurar o objetivo", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return null;
    }

    @Override
    public void addGoal(AddGoalDto goal) {
        String sql = "INSERT INTO goals (userid, goaltype, goalvalue, goalperiod, start, completed) VALUES (?, ?, ?, ?, ?, false)";
        try{
        var date = new Date(Utils.convertToDate(goal.getGoalPeriod()).getTime());
        
        
        var today = LocalDate.now();
        try (Connection con = Connect.open();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, goal.getUserId());
            ps.setString(2, goal.getGoalType().name());
            ps.setInt(3, goal.getGoalValue());
            ps.setDate(4, date);
            ps.setDate(5, new Date(today.getYear(), today.getMonth().getValue(), today.getDayOfMonth()));
            ps.execute();
            } catch (SQLException e) {
            throw new FitException("Erro interno ao adicionar o objetivo", HttpStatus.INTERNAL_SERVER_ERROR.value());
         }
        }catch (Exception e){
        throw new FitException("Erro interno ao adicionar o objetivo", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public List<Goal> listGoals() {
        String sql = "SELECT * FROM goals";
        List<Goal> goals = new ArrayList<>();
        try (Connection con = Connect.open();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                goals.add(mapGoal(rs));
            }
        } catch (SQLException e) {
            throw new FitException("Erro interno ao procurar os objetivos", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return goals;
    }

    public Goal refresh(RefreshDto ref){
        String sql = "UPDATE goals SET completed = true WHERE id = ?";
        
        try (Connection con = Connect.open();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(4, ref.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new FitException("Erro interno ao atualizar o objetivo", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        
        return findGoal(ref.getId());
    }

    @Override
    public void deleteGoal(DeleteGoalDto goal) {
        String sql = "DELETE FROM goals WHERE id = ?";
        try (Connection con = Connect.open();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, goal.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new FitException("Erro interno ao deletar o objetivo", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public Goal updateGoal(UpdateGoalDto goal) {
        String sql = "UPDATE goals SET  goaltype = ?, goalvalue = ?, goalperiod = ? WHERE id = ?";
        try{
        var date = new Date(Utils.convertToDate(goal.getGoalPeriod()).getTime());
        try (Connection con = Connect.open();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, goal.getGoalType().name());
            ps.setInt(2, goal.getGoalValue());
            ps.setDate(3, date);
            ps.setLong(4, goal.getId());
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
