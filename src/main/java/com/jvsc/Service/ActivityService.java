package com.jvsc.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvsc.fit.Dao.DaoImpl.ActivityJdbc;
import com.jvsc.fit.Entity.Activity;

@Service
public class ActivityService {
    
    @Autowired
    ActivityJdbc activityDao;

    public List<Activity> listActivities(){
        try{
            return this.activityDao.listActivities();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Activity findActivity(long id){
        try{
            return this.activityDao.findActivity(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void deleteActivity(long id){
        try{
            this.activityDao.deleteActivity(id);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Activity updateActivity(Activity activity, long id){
        try{
            return this.activityDao.updateActivity(activity, id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void addActivity(Activity activity){
        try{
            this.activityDao.addActivity(activity);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    
}
