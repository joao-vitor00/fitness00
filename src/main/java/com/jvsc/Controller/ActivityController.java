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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jvsc.Service.ActivityService;
import com.jvsc.Entity.Activity;
import com.jvsc.Request.activity.AddActivityDto;
import com.jvsc.Request.activity.DeleteActivityDto;
import com.jvsc.Request.activity.UpdateActivityDto;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    
    @Autowired
    ActivityService activityService;

    

@PostMapping("/new-activity")
    public ResponseEntity<?> addActivity(@RequestBody AddActivityDto activity) {
        activityService.addActivity(activity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findActivity(@PathVariable long id) {
        var activity = activityService.findActivity(id);
        return activity != null ? new ResponseEntity<>(activity, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteActivity(@RequestBody DeleteActivityDto activity) {
        activityService.deleteActivity(activity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/view")
    public ResponseEntity<?> listActivities() {
        var activities = activityService.listActivities();
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateActivity(@RequestBody UpdateActivityDto activity) {
        var updatedActivity = activityService.updateActivity(activity);
        return new ResponseEntity<>(updatedActivity, HttpStatus.OK);
    }
}
