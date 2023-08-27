package com.food.menu.service;

import com.food.menu.response.UserResponse;
import com.food.menu.dao.models.User;

import java.util.List;

public interface UserService {

    UserResponse getUserByID(int userID);

    List<UserResponse> getAllUsers();

    void deleteUser(int userID);

    void updateUser(int userID, User user);
}
