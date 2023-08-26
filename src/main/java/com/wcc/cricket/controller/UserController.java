package com.wcc.cricket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wcc.cricket.dao.models.User;
import com.wcc.cricket.request.RegisterRequest;
import com.wcc.cricket.response.AuthenticationResponse;
import com.wcc.cricket.response.UserResponse;
import com.wcc.cricket.service.AuthenticationService;
import com.wcc.cricket.service.UserService;
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

    @PostMapping("/users")
    public ResponseEntity<AuthenticationResponse> createUser(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
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
