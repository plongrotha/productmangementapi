package com.productmanagement.productmanagementapi.service;

import java.util.List;

import com.productmanagement.productmanagementapi.model.entity.User;

public interface UserService {

    User createUser(User user);

    List<User> AddUserBulk(List<User> users);

    List<String> getAllEmails();

}
