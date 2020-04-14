package com.trackerjob.trackerjob.controller;


import com.trackerjob.trackerjob.entity.JobAppEntity;
import com.trackerjob.trackerjob.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.concurrent.ExecutionException;

@CrossOrigin
@RestController
@RequestMapping("/jobapp")
public class ApplicationsApiController {

    @Autowired
    FirebaseService firebaseService;


    @GetMapping
    public Collection<JobAppEntity> getUserApplications() throws ExecutionException, InterruptedException {
        return firebaseService.getAllJobApp();
//        return null;
    }

    @GetMapping("/getById")
    public JobAppEntity getApplicationById(@RequestHeader String id) throws ExecutionException, InterruptedException {
        return firebaseService.getJobAppById(id);
    }

    @PostMapping
    public String addJobApplication(@RequestBody JobAppEntity jobAppEntity) throws ExecutionException, InterruptedException {
        return firebaseService.addJobApp(jobAppEntity);
    }

    @PutMapping
    public String updateJobApplication(@RequestBody JobAppEntity jobAppEntity) throws ExecutionException, InterruptedException {
        return firebaseService.updateJobApp(jobAppEntity);
    }

    @DeleteMapping
    public String deleteJobApplication(JobAppEntity jobAppEntity){
        return null;
    }


}
