package com.wcc.cricket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wcc.cricket.response.UserResponse;
import com.wcc.cricket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    final ObjectMapper mapper = new ObjectMapper();

    @GetMapping("users")
    public ResponseEntity<List<UserResponse>> getUserAllUse() {
        List<UserResponse> userResponseList = new ArrayList<>();
        userService.getAllUsers()
                .forEach((user) -> userResponseList.add(mapper.convertValue(user, UserResponse.class)));
        return ResponseEntity.ok(userResponseList);
    }

    @GetMapping("users/{userID}")
    public ResponseEntity<UserResponse> getUserByID(@PathVariable int userID) {
        UserResponse userResponse = mapper.convertValue(userService.getUserByID(userID), UserResponse.class);
        return ResponseEntity.ok(userResponse);
    }
}
