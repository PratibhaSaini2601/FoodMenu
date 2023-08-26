package com.wcc.cricket.service;

import com.wcc.cricket.dao.models.User;
import com.wcc.cricket.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse getUserByID(int userID);

    List<UserResponse> getAllUsers();

    void deleteUser(int userID);

    void updateUser(int userID, User user);
}
