package com.wcc.cricket.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wcc.cricket.dao.models.User;
import com.wcc.cricket.dao.repository.UserRepository;
import com.wcc.cricket.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public UserResponse getUserByID(int userID) {
        User user = userRepository.findById(userID).orElseThrow(() -> new UsernameNotFoundException("User not found using user ID " + userID));
        return mapper.convertValue(user, UserResponse.class);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<UserResponse> userResponseList = new ArrayList<>();
        userRepository.findAll()
                .forEach((user) -> userResponseList.add(mapper.convertValue(user, UserResponse.class)));
        return userResponseList;
    }

    @Override
    public UserResponse deleteUserById(int userID) {
        UserResponse userResponse = getUserByID(userID);
        userRepository.deleteById(userID);
        return userResponse;
    }
}
