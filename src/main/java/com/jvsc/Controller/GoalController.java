package com.jvsc.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jvsc.Request.goal.AddGoalDto;
import com.jvsc.Request.goal.DeleteGoalDto;
import com.jvsc.Request.goal.UpdateGoalDto;

import com.jvsc.Service.GoalService;
import com.jvsc.Entity.Goal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jvsc.Request.goal.RefreshDto;


@RestController
@RequestMapping("/goal")
public class GoalController {

    @Autowired
    GoalService goalService;

    @PostMapping("/new-goal")
    public ResponseEntity<?> addGoal(@RequestBody AddGoalDto goal) {
        goalService.addGoal(goal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findGoal(@PathVariable long id) {
        var goal = goalService.findGoal(id);
        return goal != null ? new ResponseEntity<>(goal, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteGoal(@RequestBody DeleteGoalDto goal) {
        goalService.deleteGoal(goal);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/view")
    public ResponseEntity<?> listGoals() {
        List<Goal> goals = goalService.listGoals();
        return new ResponseEntity<>(goals, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateGoal(@RequestBody UpdateGoalDto goal) {
        var updatedGoal = goalService.updateGoal(goal);
        return new ResponseEntity<>(updatedGoal, HttpStatus.OK);
    }
    
    @PutMapping("/refresh-goal")
    public ResponseEntity<?> refreshGoal(@RequestBody RefreshDto ref){
        var refreshedGoal = goalService.refresh(ref);
        return new ResponseEntity<>(refreshedGoal, HttpStatus.OK);
    }
}
