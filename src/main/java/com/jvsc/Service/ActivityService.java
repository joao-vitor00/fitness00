package com.jvsc.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jvsc.Dao.DaoImpl.ActivityJdbc;
import com.jvsc.Dao.DaoImpl.UserJdbc;
import com.jvsc.Entity.Activity;
import com.jvsc.Request.activity.AddActivityDto;
import com.jvsc.Request.activity.DeleteActivityDto;
import com.jvsc.Request.activity.UpdateActivityDto;
import com.jvsc.Response.Dto.ActivityDto;
import com.jvsc.Response.ErroResponse;
import com.jvsc.Response.FitException;
import com.jvsc.utils.Utils;

@Service
public class ActivityService {
    
    @Autowired
    ActivityJdbc activityDao;

    @Autowired
    UserJdbc userDao;

    public List<ActivityDto> listActivities(){
        List<ActivityDto> activities = new ArrayList<>();
        for(Activity act : this.activityDao.listActivities()){
            activities.add(new ActivityDto(act.getId(), act.getUserId(), userDao.findUser(act.getUserId()).getName(), act.getType(), act.getDuration(), act.getCalories(), act.getDate(), act.isCompleted() ));
        }
        if (activities.isEmpty()) {
            throw new FitException("Nenhuma atividade encontrada", HttpStatus.NOT_FOUND.value());
        }
        return activities;
        
    }

    public ActivityDto findActivity(long id){
        
        var act = this.activityDao.findActivity(id);
        return new ActivityDto(act.getId(), act.getUserId(), userDao.findUser(act.getUserId()).getName(), act.getType(), act.getDuration(), act.getCalories(), act.getDate(), act.isCompleted() );
        
        
    }

    public void deleteActivity(DeleteActivityDto activity){
        
        if(activity.getId() == 0){
            throw new FitException("O ID da atividade deve ser informado", HttpStatus.BAD_REQUEST.value());
        }

        if(this.activityDao.findActivity(activity.getId()) == null){
            throw new FitException("Atividade inexistente", HttpStatus.NOT_FOUND.value());
        }

        

        this.activityDao.deleteActivity(activity.getId());
        
    }

    public Activity updateActivity(UpdateActivityDto activity){
        if(validateActivity(activity)){
            return this.activityDao.updateActivity(activity);
        }
        return null;
    }

    public void addActivity(AddActivityDto activity){
        
        if(validateActivity(activity)){
            this.activityDao.addActivity(activity);
        }
    }

    private boolean validateActivity(AddActivityDto activity){
        List<ErroResponse> errors = new ArrayList<>();
        
        if (activity.getType() == null || activity.getType().isEmpty()) {
            errors.add(new ErroResponse("O tipo da atividade deve ser informado", HttpStatus.BAD_REQUEST.value()));
        }
        if (activity.getDuration() == 0) {
            errors.add(new ErroResponse("A duração da atividade deve ser informada", HttpStatus.BAD_REQUEST.value()));
        }
        if (activity.getCalories() == 0) {
            errors.add(new ErroResponse("A quantidade de calorias da atividade deve ser informada", HttpStatus.BAD_REQUEST.value()));
        }
        if (activity.getDate() == null) {
            errors.add(new ErroResponse("A data da atividade deve ser informada", HttpStatus.BAD_REQUEST.value()));
        }
        if (activity.getUserId() == 0) {
            errors.add(new ErroResponse("O id do usuário deve ser informado", HttpStatus.BAD_REQUEST.value()));
        }else if (userDao.findUser(activity.getUserId()) == null) {
            errors.add(new ErroResponse("O usuário informado não existe", HttpStatus.BAD_REQUEST.value()));
        }
        if(Utils.isValidDate(activity.getDate()) == false){
            errors.add(new ErroResponse("Data inválida", HttpStatus.BAD_REQUEST.value()));
        }
        if (!errors.isEmpty()) {    
            throw new FitException(errors, HttpStatus.BAD_REQUEST.value());
        }
        return true;
    }
    
    public boolean validateActivity(UpdateActivityDto activity){
        List<ErroResponse> errors = new ArrayList<>();
        if(activityDao.findActivity(activity.getId()) == null){
            throw new FitException("Atividade não encontrada", HttpStatus.BAD_REQUEST.value());
        }
        if (activity.getType() == null || activity.getType().isEmpty()) {
            errors.add(new ErroResponse("O tipo da atividade deve ser informado", HttpStatus.BAD_REQUEST.value()));
        }
        if (activity.getDuration() == 0) {
            errors.add(new ErroResponse("A duração da atividade deve ser informada", HttpStatus.BAD_REQUEST.value()));
        }
        if (activity.getCalories() == 0) {
            errors.add(new ErroResponse("A quantidade de calorias da atividade deve ser informada", HttpStatus.BAD_REQUEST.value()));
        }
        if (activity.getDate() == null) {
            errors.add(new ErroResponse("A data da atividade deve ser informada", HttpStatus.BAD_REQUEST.value()));
        }
        if(Utils.isValidDate(activity.getDate()) == false) {
            errors.add(new ErroResponse("Data inválida", HttpStatus.BAD_REQUEST.value()));
        }
        if (!errors.isEmpty()) {    
            throw new FitException(errors, HttpStatus.BAD_REQUEST.value());
        }
        
        return true;
    }
}