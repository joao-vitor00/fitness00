package com.jvsc.fit.Dao.DaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jvsc.fit.Dao.Connect;
import com.jvsc.fit.Dao.UserDao;
import com.jvsc.fit.Entity.User;

public class UserJdbc implements UserDao {

    @Autowired
    Connect con;

    @Override
    public void addUser() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User findUser(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteUser(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<User> listUsers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User updateUser(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
