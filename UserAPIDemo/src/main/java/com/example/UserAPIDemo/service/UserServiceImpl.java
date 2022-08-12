package com.example.UserAPIDemo.service;

import com.example.UserAPIDemo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * This process all customer provided user data which is stored in database.
 * Also returns user information requested by the customer.
 */
@Service
public class UserServiceImpl {
    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    @Lazy
    UserService userService;

      public List<User> getAllUsers() {
          log.info("Get all users from the DB:");
        List<User> userList = new ArrayList<User>();
          userService.findAll().forEach(user->userList.add(user));
          return userList;
    }

    public User getUserByID(String id)  {
        log.info("Get user by user id:");
        Optional<User> user = userService.findById(id);
        return user.isPresent() ? user.get() :null ;
    }

    public void saveOrUpdate(User user)
    {
        log.info("Saving user information to DB:");
        userService.save(user);
    }
}
