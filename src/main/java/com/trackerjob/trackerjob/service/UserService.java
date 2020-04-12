package com.trackerjob.trackerjob.service;

import com.trackerjob.trackerjob.dao.UserDao;
import com.trackerjob.trackerjob.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public Collection<UserEntity> getAllUsers(){
        return userDao.getAllUsers();
    }

    public void addUser(UserEntity userEntity){
        userDao.addUser(userEntity);
        return;
    }

    public void updateUser(UserEntity userEntity){
        userDao.updateUser(userEntity);
        return;
    }
}
