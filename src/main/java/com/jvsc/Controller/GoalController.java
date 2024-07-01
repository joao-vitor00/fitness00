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

import com.jvsc.Service.GoalService;
import com.jvsc.fit.Entity.Goal;

@RestController("/goal")
public class GoalController {

    @Autowired
    GoalService goalService;

    @PostMapping("/new-goal")
    public ResponseEntity<Void> addGoal(@RequestBody Goal goal) {
        goalService.addGoal(goal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Goal> findGoal(@PathVariable long id) {
        Goal goal = goalService.findGoal(id);
        return goal != null ? new ResponseEntity<>(goal, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable long id) {
        goalService.deleteGoal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/view")
    public ResponseEntity<List<Goal>> listGoals() {
        List<Goal> goals = goalService.listGoals();
        return new ResponseEntity<>(goals, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Goal> updateGoal(@PathVariable long id, @RequestBody Goal goal) {
        Goal updatedGoal = goalService.updateGoal(goal, id);
        return new ResponseEntity<>(updatedGoal, HttpStatus.OK);
    }
    
}
