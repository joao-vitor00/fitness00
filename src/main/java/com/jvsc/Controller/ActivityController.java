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

import com.jvsc.Service.ActivityService;
import com.jvsc.fit.Entity.Activity;

@RestController("/activity")
public class ActivityController {
    
    @Autowired
    ActivityService activityService;

    

@PostMapping("/new-activity")
    public ResponseEntity<Void> addActivity(@RequestBody Activity activity) {
        activityService.addActivity(activity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Activity> findActivity(@PathVariable long id) {
        Activity activity = activityService.findActivity(id);
        return activity != null ? new ResponseEntity<>(activity, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable long id) {
        activityService.deleteActivity(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/view")
    public ResponseEntity<List<Activity>> listActivities() {
        List<Activity> activities = activityService.listActivities();
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable long id, @RequestBody Activity activity) {
        Activity updatedActivity = activityService.updateActivity(activity, id);
        return new ResponseEntity<>(updatedActivity, HttpStatus.OK);
    }
}
