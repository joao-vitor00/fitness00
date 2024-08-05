package com.jvsc.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jvsc.Dao.DaoImpl.ActivityJdbc;
import com.jvsc.Dao.DaoImpl.GoalJdbc;
import com.jvsc.Dao.DaoImpl.UserJdbc;
import com.jvsc.Entity.Activity;
import com.jvsc.Entity.Goal;
import com.jvsc.Request.goal.AddGoalDto;
import com.jvsc.Request.goal.DeleteGoalDto;
import com.jvsc.Request.goal.RefreshDto;
import com.jvsc.Request.goal.UpdateGoalDto;
import com.jvsc.Response.ErroResponse;
import com.jvsc.Response.FitException;
import com.jvsc.enums.GoalType;
import com.jvsc.utils.Utils;

@Service
public class GoalService {

    @Autowired
    GoalJdbc goalJdbc;

    @Autowired
    ActivityJdbc actJdbc;

    @Autowired
    UserJdbc userDao;
    
    public void addGoal(AddGoalDto goal) {
        if(validateGoal(goal)){
            goalJdbc.addGoal(goal);
        }
    }

    public void deleteGoal(DeleteGoalDto goal) {
        if(goalJdbc.findGoal(goal.getId()) == null) {
            throw new FitException("O objetivo informado não existe", HttpStatus.NOT_FOUND.value());
        }
        
        goalJdbc.deleteGoal(goal);
    }

    public Goal updateGoal(UpdateGoalDto goal) {
        if(validateGoal(goal)){
            return goalJdbc.updateGoal(goal);
        }

        return null;
    }

    public Goal findGoal(long id) {
        
        var goal = goalJdbc.findGoal(id);
        if(goal == null) {
            throw new FitException("O objetivo informado não existe", HttpStatus.NOT_FOUND.value());
        }
        return goal;
        
    }

    public List<Goal> listGoals() {
        
        var list = goalJdbc.listGoals();
        if (list.isEmpty()) {
            throw new FitException("Nenhum objetivo encontrada", HttpStatus.NOT_FOUND.value());
        }

        return list;
    }

    public Goal refresh(RefreshDto ref){
        var goal = goalJdbc.findGoal(ref.getId());
        if(goal == null){
            throw new FitException("objetivo não encontrado", HttpStatus.NOT_FOUND.value());
        }
        var user = userDao.findUser(goal.getUserId());

        
        
        
        var acts = actJdbc.listByUser(goal.getUserId());

        if (acts == null) {
            throw new FitException("nenhuma atividade foi encontrada para este usuário", HttpStatus.NOT_FOUND.value());
        }
        int values = 0;
        
        for(Activity act : acts){
            
            if(act.getDate().after(goal.getStart()) && act.getDate().before(goal.getStart())){
                if(goal.getGoalType()==GoalType.CALORIAS){
                    values += act.getCalories();
                }else{
                    values += act.getDuration();
                }    
            }    
        }
        if(values < goal.getGoalValue()){
            throw new FitException("Não é necessário atualizar este objetivo", HttpStatus.NOT_MODIFIED.value());
        }
        

        return goalJdbc.refresh(ref);
    }

    public List<Goal> listCompletedGoals() {
        
        List<Goal> list = new ArrayList<>();
        for (Goal g : goalJdbc.listGoals()) {
            if (g.isCompleted()) {
                list.add(g);
            }
        }

        if (list.isEmpty()) {
            throw new FitException("Nenhum objetivo encontrada", HttpStatus.NOT_FOUND.value());
        }
        return list;
        
        
    }

    public boolean validateGoal (AddGoalDto goal){
        List<ErroResponse> errors = new ArrayList<>();
        if (goal.getGoalValue() <= 0) {
            errors.add(new ErroResponse("o valor do objetivo deve ser maior que zero", HttpStatus.BAD_REQUEST.value()));
        }
        if (goal.getGoalPeriod() == null) {
            errors.add(new ErroResponse("a data do objetivo deve ser informada", HttpStatus.BAD_REQUEST.value()));
            
        }
        if(goal.getGoalType() == null){
            errors.add(new ErroResponse("o tipo do objetivo deve ser informado", HttpStatus.BAD_REQUEST.value()));
        }
        if(goal.getGoalValue() == 0 ){
            errors.add(new ErroResponse("o valor do objetivo deve ser informado", HttpStatus.BAD_REQUEST.value()));
        }
        
        if (!errors.isEmpty()) {
            throw new FitException(errors, HttpStatus.BAD_REQUEST.value());
        }

        
        return true;
    }

    public boolean validateGoal (UpdateGoalDto goal){
        List<ErroResponse> errors = new ArrayList<>();
        if(goalJdbc.findGoal(goal.getId()) == null){
            errors.add(new ErroResponse("O objetivo informado não existe", HttpStatus.NOT_FOUND.value()));
        }
        if (goal.getGoalValue() <= 0) {
            errors.add(new ErroResponse("o valor do objetivo deve ser maior que zero", HttpStatus.BAD_REQUEST.value()));
        }
        if (goal.getGoalPeriod() == null) {
            errors.add(new ErroResponse("a data do objetivo deve ser informada", HttpStatus.BAD_REQUEST.value()));
            
        }
        if(goal.getGoalType() == null){
            errors.add(new ErroResponse("o tipo do objetivo deve ser informado", HttpStatus.BAD_REQUEST.value()));
        }
        if(goal.getGoalValue() == 0 ){
            errors.add(new ErroResponse("o valor do objetivo deve ser informado", HttpStatus.BAD_REQUEST.value()));
        }
        if(Utils.isValidDate(goal.getGoalPeriod()) == false){
            errors.add(new ErroResponse("a data informada é inválida", HttpStatus.BAD_REQUEST.value()));
        }
        if (!errors.isEmpty()) {
            throw new FitException(errors, HttpStatus.BAD_REQUEST.value());
        }

        
        return true;
    }
    
}
