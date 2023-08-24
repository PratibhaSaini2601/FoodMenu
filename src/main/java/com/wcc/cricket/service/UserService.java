package com.wcc.cricket.service;

import com.wcc.cricket.dao.models.User;

import java.util.List;

public interface UserService {

    User getUserByID(int userID);

    List<User> getAllUsers();
}
