package com.jvsc.fit.Dao.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jvsc.fit.Dao.Connect;
import com.jvsc.fit.Dao.UserDao;
import com.jvsc.fit.Entity.User;

@Repository
public class UserJdbc implements UserDao {


    @Override
    public void addUser(User user) throws SQLException{
        try {
            Connection con = Connect.open();
            String sql = "INSERT INTO users (name, email, password, birth_date) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql.toUpperCase());
            ps.setString(1, user.getName().toUpperCase());
            ps.setString(2, user.getEmail().toUpperCase());
            ps.setString(3, user.getPassword().toUpperCase());
            ps.setString(4, user.getBirthDate().toString().toUpperCase());
            ps.execute();
        }catch(Exception e ){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public User findUser(long id) throws SQLException {
    	String sql = "SELECT * FROM users WHERE id = ?";
        Connection con = Connect.open();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery(); 
        if (rs.next()) {
        	return mapUser(rs);
        }
            
        
        return null;
    }

    @Override
    public void deleteUser(long id) throws SQLException{
    	String sql = "DELETE FROM users WHERE id = ?";
    	
    	Connection con = Connect.open();
    	PreparedStatement ps = con.prepareStatement(sql.toUpperCase());
    	ps.setLong(1, id);
    	ps.execute();
    	
    }

    @Override
    public List<User> listUsers() throws SQLException{
    	String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        Connection con = Connect.open();
    	PreparedStatement ps = con.prepareStatement(sql.toUpperCase());
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
        	users.add(mapUser(rs));
        }
        
        return users;
    }

    @Override
    public User updateUser(User user ,long id) throws SQLException{
    	String sql = "UPDATE users SET name = ?, email = ?, password = ?, birth_date = ? WHERE id = ?";
    	Connection con = Connect.open();
    	PreparedStatement ps = con.prepareStatement(sql.toUpperCase());
    	ps.setString(1, user.getName().toUpperCase());
    	ps.setString(2, user.getEmail().toUpperCase());
    	ps.setString(3, user.getPassword().toUpperCase());
    	ps.setString(4, user.getBirthDate().toString().toUpperCase());
    	ps.setLong(5, id);
    	ps.execute();
    	
    	return findUser(id);
    	
    }
    
    private User mapUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setBirthDate(rs.getDate("birth_date"));
        return user;
    }
    
}
