package com.trackerjob.trackerjob.dao;

import com.trackerjob.trackerjob.entity.UserEntity;

import java.util.Collection;

public interface UserDao {
    Collection<UserEntity> getAllUsers();
    void addUser(UserEntity userEntity);
    void updateUser(UserEntity userEntity);
    void deleteUser(UserEntity userEntity);
    UserEntity getUserById(UserEntity userEntity);

}
