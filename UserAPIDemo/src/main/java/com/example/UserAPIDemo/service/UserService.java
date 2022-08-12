package com.example.UserAPIDemo.service;

import com.example.UserAPIDemo.User;
import org.springframework.data.repository.CrudRepository;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public interface UserService extends CrudRepository<User,String> {

    List<User> getAllUsers();
    User getUserByID(String id);
    void saveOrUpdate(User user);


}
