package com.example.b93Rest.repository;

import com.example.b93Rest.dtos.GetUserResponse;
import com.example.b93Rest.model.User;

import java.util.HashMap;
import java.util.random.RandomGenerator;

public class UserRepository {

    HashMap<Integer,User>db = new HashMap<>();

    public User create(User user) {
        Integer id = RandomGenerator.getDefault().nextInt();
        user.setId(id);
        if(!db.containsKey(id)){
            db.put(id,user);
        }
        return user;

    }

    public User getUser(Integer id) {
        if(!db.containsKey(id)){
            return null;
        }
        return db.get(id);
    }

    public User update(User user, Integer id) {
        if(!db.containsKey(id)){
            return null;
        }
        user.setId(id);
        db.put(id,user);
        return user;


    }


}
