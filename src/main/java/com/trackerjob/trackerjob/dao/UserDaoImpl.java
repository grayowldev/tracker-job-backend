package com.trackerjob.trackerjob.dao;

import com.trackerjob.trackerjob.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class UserDaoImpl implements UserDao {

    private static Map<String, UserEntity> userMapDb;
    static {
        userMapDb = new HashMap<String, UserEntity>();
        UserEntity user1 = new UserEntity("Sam Smith","SamSmith@trackerjob.com");
        UserEntity user2 = new UserEntity("Mary Lee","MaryLee@trackerjob.com");
        UserEntity user3 = new UserEntity("Joan Smith","JoanSmith@trackerjob.com");
        UserEntity user4 = new UserEntity("Tim Mitt","TimMitt@trackerjob.com");

        userMapDb.put(user1.getId(),user1);
        userMapDb.put(user2.getId(),user2);
        userMapDb.put(user3.getId(),user3);
        userMapDb.put(user4.getId(),user4);
    }

    @Override
    public Collection<UserEntity> getAllUsers() {
        return userMapDb.values();
    }

    @Override
    public void addUser(UserEntity userEntity) {
        userMapDb.put(userEntity.getId(), userEntity);
        return;
    }

    @Override
    public void updateUser(UserEntity userEntity) {
        UserEntity user = userMapDb.get(userEntity.getId());
        if (userEntity.getAccountStatus() != null){
            user.setAccountStatus(userEntity.getAccountStatus());
        }
        if (userEntity.getAccountType() != null){
            user.setAccountType(userEntity.getAccountType());
        }
        if (userEntity.getAge() != null){
            user.setAge(userEntity.getAge());
        }
        if (userEntity.getEmail() != null){
            user.setEmail(userEntity.getEmail());
        }
        if (userEntity.getName() != null){
            user.setName(userEntity.getName());
        }
        if (userEntity.getOccupation() != null){
            user.setOccupation(userEntity.getOccupation());
        }
        userMapDb.put(userEntity.getId(), user);
        return;
    }

    @Override
    public void deleteUser(UserEntity userEntity) {
        userMapDb.remove(userEntity.getId());
        return;
    }

    @Override
    public UserEntity getUserById(UserEntity userEntity) {
        return userMapDb.get(userEntity.getId());
    }
}
