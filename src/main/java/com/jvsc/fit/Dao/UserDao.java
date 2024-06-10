package com.jvsc.fit.Dao;

import java.util.List;

import com.jvsc.fit.Entity.User;

public interface UserDao {
	
	public void addUser();
	public User findUser(long id);
	public void deleteUser(long id);
	public List<User> listUsers();
	public User updateUser(long id);

}
