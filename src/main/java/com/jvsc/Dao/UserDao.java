package com.jvsc.Dao;

import java.util.List;

import com.jvsc.Entity.Usuario;
import com.jvsc.Request.user.AddUserDto;
import com.jvsc.Request.user.DeleteUserDto;
import com.jvsc.Request.user.UpdateUserDto;
import com.jvsc.Response.Dto.UserDto;

public interface UserDao {
	
	public void addUser(AddUserDto user) ;
	public Usuario findUser(long id) ;
	public void deleteUser(DeleteUserDto user) ;
	public List<UserDto> listUsers() ;
	public Usuario updateUser(UpdateUserDto user)  ;

}
