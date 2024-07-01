package com.jvsc.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvsc.fit.Dao.DaoImpl.UserJdbc;
import com.jvsc.fit.Entity.User;

@Service
public class UserService {
    
    @Autowired
    UserJdbc userDao;

    public User findUser(long id){
        try{
           
        return this.userDao.findUser(id);
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public void deleteUser(long id){
        try{
        this.userDao.deleteUser(id);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void addUser(User user){
        try{
        this.userDao.addUser(user);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<User> listUsers(){
        try{
        return this.userDao.listUsers();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public User updateUser(User user, long id){
        try{
        return this.userDao.updateUser(user, id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    
}
