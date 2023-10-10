package com.food.menu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.food.menu.request.UserSummaryRequest;
import com.food.menu.response.UserResponse;
import com.food.menu.dao.models.User;
import com.food.menu.service.AuthenticationService;
import com.food.menu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    final ObjectMapper mapper = new ObjectMapper();

    @GetMapping("users")
    public ResponseEntity<List<UserResponse>> getUserAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("users/{userID}")
    public ResponseEntity<UserResponse> getUserByID(@PathVariable int userID) {
        return ResponseEntity.ok(userService.getUserByID(userID));
    }

    @PostMapping("/users/summary")
    public ResponseEntity<List<UserResponse>> getUsersSummary(@RequestBody UserSummaryRequest userSummaryRequest) {
        return ResponseEntity.ok(userService.getUsersSummary(userSummaryRequest));
    }

    @PatchMapping("users/{userID}")
    public void updateUser(@PathVariable int userID, @RequestBody User user) {
        userService.updateUser(userID, user);
    }

    @DeleteMapping("users/{userID}")
    public void deleteUser(@PathVariable int userID) {
        userService.deleteUser(userID);
    }
}
