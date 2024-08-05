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
        String sql = "INSERT INTO users (name, email, password, birthdate) VALUES (?, ?, ?, ?)";
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
        String sql = "SELECT * FROM users WHERE id = ?";
        try  {
            Connection con = Connect.open();
            PreparedStatement cs = con.prepareStatement(sql);
            cs.setLong(1, id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                return mapUser(rs);
            }
        } catch (SQLException e) {
           
            throw new FitException("Erro interno ao procurar o usuário", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return null;
    }

    public Usuario findName(String name) {
        String sql = "SELECT * FROM users WHERE name = ?";
        try  {
            Connection con = Connect.open();
            PreparedStatement cs = con.prepareStatement(sql);
            cs.setString(1, name);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                return mapUser(rs);
            }
        } catch (SQLException e) {
           
            throw new FitException("Erro interno ao procurar o usuário", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return null;
    }

    public Usuario findEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection con = Connect.open();
             PreparedStatement cs = con.prepareStatement(sql)) {
            cs.setString(1, email);
            ResultSet rs = cs.executeQuery();
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
        String sql = "delete from users where id = ? ";
        try (Connection con = Connect.open();
             PreparedStatement cs = con.prepareStatement(sql)) {
            cs.setLong(1, user.getId());
            
            cs.executeUpdate();
        } catch (SQLException e) {
            
            throw new FitException("Erro interno ao deletar o usuário", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public List<UserDto> listUsers() {
        String sql = "SELECT * FROM users";
        List<UserDto> users = new ArrayList<>();
        try (Connection con = Connect.open();
             PreparedStatement cs = con.prepareStatement(sql)) {
            ResultSet rs = cs.executeQuery();
            
                
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
        String sql = "UPDATE users SET name = ?, email = ?, password = ?, birthdate = ? WHERE id = ? ";
        Date date;
        try {
            date = new Date(Utils.convertToDate(user.getBirthDate()).getTime());
        } catch (ParseException e) {
            
            throw new FitException("Erro interno ao atualizar o usuário", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        try (Connection con = Connect.open();
             PreparedStatement cs = con.prepareStatement(sql)) {
            cs.setLong(4, user.getId());
            cs.setString(1, user.getName());
            cs.setString(2, user.getEmail());
            cs.setString(3, user.getNewPassword());
            
            cs.setDate(4, date);
            cs.executeUpdate();
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
