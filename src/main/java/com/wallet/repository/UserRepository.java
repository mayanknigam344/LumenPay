package com.wallet.repository;


import com.wallet.exception.DuplicateUserException;
import com.wallet.exception.UserNotFoundException;
import com.wallet.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class UserRepository {

    HashMap<String, User> users = new HashMap<>();

    public void addUser(User user){
        if (users.containsKey(user.getUserId())) {
            throw new DuplicateUserException("User with ID " + user.getUserId() + " already exists.");
        }
        users.put(user.getUserId(), user);
    }

    public User getUser(String userId){
        User user = users.get(userId);
        if (user == null) {
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }
        return user;
    }
}
