package com.wcc.cricket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wcc.cricket.response.UserResponse;
import com.wcc.cricket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    final ObjectMapper mapper = new ObjectMapper();

    @GetMapping("users")
    public ResponseEntity<List<UserResponse>> getUserAllUse() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("users/{userID}")
    public ResponseEntity<UserResponse> getUserByID(@PathVariable int userID) {
        return ResponseEntity.ok(userService.getUserByID(userID));
    }

    @DeleteMapping("users/{userID}")
    public ResponseEntity<UserResponse> deleteUserByID(@PathVariable int userID) {
        return ResponseEntity.ok(userService.deleteUserById(userID));
    }
}
