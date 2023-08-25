package com.wcc.cricket.service;

import com.wcc.cricket.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse getUserByID(int userID);

    List<UserResponse> getAllUsers();

    UserResponse deleteUserById(int userID);
}
