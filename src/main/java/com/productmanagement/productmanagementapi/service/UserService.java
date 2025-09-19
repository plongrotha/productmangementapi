package com.productmanagement.productmanagementapi.service;

import java.util.List;

import com.productmanagement.productmanagementapi.model.entity.User;

public interface UserService {

    User createUser(User user);

    List<String> getAllEmails();

    User getUserById(long id);

    User updateUserById(long id, User user);

    List<User> getAllUsers();

    void deleteUserById(long id);

    List<User> createUsersBulk(List<User> users);
}
