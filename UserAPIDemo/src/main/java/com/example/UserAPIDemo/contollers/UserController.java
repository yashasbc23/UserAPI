package com.example.UserAPIDemo.contollers;

import com.example.UserAPIDemo.TrackExecutionTime;
import com.example.UserAPIDemo.User;
import com.example.UserAPIDemo.exceptions.UserNotFoundException;
import com.example.UserAPIDemo.service.UserServiceImpl;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Validated

public class UserController {

    Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    @Lazy
    UserServiceImpl userServiceImpl;
    UUID uuid= UUID.randomUUID();

    /**
     * Sample test data.
     * @return
     */
    @GetMapping("/user")
    public User user(){
         return new User(uuid.toString(),"Yash", "Bc","t","email","0","","");
    }

    /**
     * It returns all users present in the database.
     */
    @GetMapping("getAllUsers")
    @TrackExecutionTime
    public List<User> getAllUsers(){
        log.info("Getting all Users:");
        return  userServiceImpl.getAllUsers();
    }

    /**
     * Get particualr user information which has requested.
     */
    @TrackExecutionTime
    @GetMapping("/findUserById/{id}")
    public User getUserById(@Valid @PathVariable String id){
        log.info("Get a given userid-"+id);
        User userFromDB = userServiceImpl.getUserByID(id);
        if(userFromDB==null){
            List<String> errors = new ArrayList<String>();
            new UserNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR,errors,"User not found. ID-"+id);
        }
       return userFromDB;
    }

    /**
     * Adds user details to database.
     */
    @TrackExecutionTime
    @PostMapping("/addUser")
    public void addUser(@Valid @RequestBody User user){
        log.info("Add user/users to the system:");
        log.info("Requested user to add:"+new JSONObject(user));
        userServiceImpl.saveOrUpdate(user);
    }

}
