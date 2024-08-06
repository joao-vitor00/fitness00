package com.jvsc.Dao.DaoImpl;

import java.sql.CallableStatement;
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

import com.jvsc.Dao.Connect;
import com.jvsc.Dao.UserDao; 
import com.jvsc.Entity.Usuario;
import com.jvsc.Request.user.AddUserDto;
import com.jvsc.Request.user.DeleteUserDto;
import com.jvsc.Request.user.UpdateUserDto;
import com.jvsc.Response.Dto.UserDto;
import com.jvsc.Response.FitException;
import com.jvsc.utils.Utils;

@Repository
public class UserJdbc implements UserDao {
    
    @Override
    public void addUser(AddUserDto user) {
        String sql = "call add_user(?, ?, ?, ?)";
        try {
            var date = new Date(Utils.convertToDate(user.getBirthDate()).getTime());
            try (Connection con = Connect.open();
                 PreparedStatement cs = con.prepareStatement(sql)) {
                cs.setString(1, user.getName());
                cs.setString(2, user.getEmail());
                cs.setString(3, user.getPassword());
                cs.setDate(4, date);
                cs.execute();
            } catch (SQLException e) {
                
                throw new FitException("Erro interno ao adicionar o usuário", HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        } catch (ParseException ex) {
            
            throw new FitException("Erro interno ao adicionar o usuário", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public Usuario findUser(long id) {
        String sql = "{call find_user(?)}";
        try  {
            Connection con = Connect.open();
            CallableStatement cs = con.prepareCall(sql);
            cs.setLong(1, id);
            cs.execute();
            ResultSet rs = cs.getResultSet();
            if (rs.next()) {
                return mapUser(rs);
            }
        } catch (SQLException e) {
           
            throw new FitException("Erro interno ao procurar o usuário", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return null;
    }

    public Usuario findName(String name) {
        String sql = "{call find_user_by_name(?)}";
        try (Connection con = Connect.open();
        CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, name);
            cs.execute();
            ResultSet rs = cs.getResultSet();
            if (rs.next()) {
                return mapUser(rs);
            }
        } catch (SQLException e) {
           
            throw new FitException("Erro interno ao procurar o usuário", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return null;
    }

    public Usuario findEmail(String email) {
        String sql = "{call find_user_by_email(?)}";
        try (Connection con = Connect.open();
        CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, email);
            cs.execute();
            ResultSet rs = cs.getResultSet();
            if (rs.next()) {
                return mapUser(rs);
            }
        } catch (SQLException e) {
            
            throw new FitException("Erro interno ao procurar o usuário", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return null;
    }

    @Override
    public void deleteUser(DeleteUserDto user) {
        String sql = "call delete_user(?)";
        try (Connection con = Connect.open();
             CallableStatement cs = con.prepareCall(sql)){
            cs.setLong(1, user.getId());
            
            cs.execute();
        } catch (SQLException e) {
            
            throw new FitException("Erro interno ao deletar o usuário", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public List<UserDto> listUsers() {
        String sql = "{call list_users()}";
        List<UserDto> users = new ArrayList<>();
        try (Connection con = Connect.open();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.execute();
            ResultSet rs = cs.getResultSet();
            
                
                    while (rs.next()) {
                        users.add(mapUserDto(rs));
                    }
                
            
        } catch (SQLException e) {
            
            throw new FitException("Erro interno ao procurar os usuários", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return users;
    }

    @Override
    public Usuario updateUser(UpdateUserDto user) {
        String sql = "call update_user(?, ?, ?, ?, ?) ";
        Date date;
        try {
            date = new Date(Utils.convertToDate(user.getBirthDate()).getTime());
        } catch (ParseException e) {
            
            throw new FitException("Erro interno ao atualizar o usuário", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        try (Connection con = Connect.open();
             PreparedStatement cs = con.prepareStatement(sql)) {
            cs.setLong(1, user.getId());
            cs.setString(2, user.getName());
            cs.setString(3, user.getEmail());
            cs.setString(4, user.getNewPassword());
            
            cs.setDate(5, date);
            cs.execute();
        } catch (SQLException e) {
            
            throw new FitException("Erro interno ao atualizar o usuário", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return findUser(user.getId());
    }

    private Usuario mapUser(ResultSet rs) {
        try {
            Usuario user = new Usuario();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setBirthDate(rs.getDate("birthdate"));
            return user;
        } catch (SQLException e) {
            
            throw new FitException("Erro interno ao mapear o usuário", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    private UserDto mapUserDto(ResultSet rs) {
        try {
            UserDto user = new UserDto();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setBirthDate(rs.getDate("birthdate"));
            return user;
        } catch (SQLException e) {
            
            throw new FitException("Erro interno ao mapear o usuário", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
