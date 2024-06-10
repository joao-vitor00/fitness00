package com.jvsc.fit.Dao;

import java.sql.SQLException;
import java.util.List;

import com.jvsc.fit.Entity.User;

public interface UserDao {
	
	public void addUser(User user) throws SQLException;
	public User findUser(long id) throws SQLException;
	public void deleteUser(long id) throws SQLException;
	public List<User> listUsers() throws SQLException;
	public User updateUser(User user, long id) throws SQLException ;

}
