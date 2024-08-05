package com.jvsc.Dao.DaoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.jvsc.Dao.ActivitiesDao;
import com.jvsc.Dao.Connect;
import com.jvsc.Entity.Activity;
import com.jvsc.Request.activity.AddActivityDto;
import com.jvsc.Request.activity.UpdateActivityDto;
import com.jvsc.Response.FitException;
import com.jvsc.utils.Utils;

@Repository
public class ActivityJdbc implements ActivitiesDao {

    

    @Override
    public void addActivity(AddActivityDto activity)  {
        String sql = "INSERT INTO activities (userid, type, duration, calories, date, completed) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = Connect.open();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, activity.getUserId());
            ps.setString(2, activity.getType());
            ps.setInt(3, activity.getDuration());
            ps.setInt(4, activity.getCalories());
            try {
                ps.setDate(5, new Date(Utils.convertToDate(activity.getDate()).getTime()));
            } catch (ParseException e) {
                
                
                throw new FitException("Erro ao adicionar a atividade", HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
            ps.setBoolean(6, activity.isCompleted());
            ps.executeUpdate();
        } catch (SQLException e) {
            
            throw new FitException("Erro ao adicionar a atividade", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public void deleteActivity(long id)  {
        String sql = "DELETE FROM activities WHERE id = ? ";
        try (Connection con = Connect.open();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            
            throw new FitException("Erro ao deletar a atividade", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public Activity findActivity(long id) {
        String sql = "SELECT * FROM activities WHERE id = ?";
        try (Connection con = Connect.open();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapActivity(rs);
                }
            }
        } catch (SQLException e) {
            
            throw new FitException("Erro ao procurar a atividade", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return null;
    }

    @Override
    public List<Activity> listActivities()  {
        String sql = "SELECT * FROM activities";
        List<Activity> act = new ArrayList<>();
        try (Connection con = Connect.open();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                act.add(mapActivity(rs));
            }
        } catch (SQLException e) {
            
            throw new FitException("Erro ao listar as atividades", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return act;
    }

    public List<Activity> listByUser(long id)  {
        String sql = "SELECT * FROM activities where userId = ? and completed = true";
        List<Activity> act = new ArrayList<>();
        try (Connection con = Connect.open();
             PreparedStatement ps = con.prepareStatement(sql)){
             ps.setLong(1, id);
             ResultSet rs = ps.executeQuery(); 
            while (rs.next()) {
                act.add(mapActivity(rs));
            }
        } catch (SQLException e) {
            
            throw new FitException("Erro ao listar as atividades", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return act;
    }

    @Override
    public Activity updateActivity(UpdateActivityDto activity) { 
        String sql = "UPDATE activities SET type = ?, duration = ?, calories = ?, date = ?, completed = ? WHERE id = ?";
        try (Connection con = Connect.open();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(2, activity.getType());
            ps.setInt(3, activity.getDuration());
            ps.setInt(4, activity.getCalories());
            try {
                ps.setDate(5, new Date(Utils.convertToDate(activity.getDate()).getTime()));
            } catch (ParseException e) {
                
                
                throw new FitException("Erro ao atualizar a atividade", HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
            ps.setBoolean(6, activity.isCompleted());
            ps.setLong(1, activity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            
            throw new FitException("Erro ao atualizar a atividade", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return findActivity(activity.getId());
    
    }

    private Activity mapActivity(ResultSet rs)  {
        Activity activity = new Activity();
        try {
        
        activity.setId(rs.getLong("id"));
        activity.setUserId(rs.getLong("userid"));
        activity.setType(rs.getString("type"));
        activity.setDuration(rs.getInt("duration"));
        activity.setCalories(rs.getInt("calories"));
        activity.setDate(rs.getDate("date"));
		activity.setCompleted(rs.getBoolean("completed"));
		} catch (SQLException e) {
			
			throw new FitException("Erro interno ao mapear a atividade", HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
        return activity;
    }
}
