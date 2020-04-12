package com.trackerjob.trackerjob.controller;

import com.trackerjob.trackerjob.entity.UserEntity;
import com.trackerjob.trackerjob.service.FirebaseService;
import com.trackerjob.trackerjob.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/user")
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    FirebaseService firebaseService;

    @GetMapping
    public Collection<UserEntity> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public String addUser(@RequestBody UserEntity userEntity) throws ExecutionException, InterruptedException {
//        userEntity.setRandomId();
//        userService.addUser(userEntity);
        return firebaseService.saveUserDetails(userEntity);
    }

    @PutMapping
    public void updateUser(@RequestBody UserEntity userEntity){
        userService.updateUser(userEntity);
    }
}
