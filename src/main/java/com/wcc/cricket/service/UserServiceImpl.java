package com.wcc.cricket.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wcc.cricket.dao.models.User;
import com.wcc.cricket.dao.repository.UserRepository;
import com.wcc.cricket.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
    public void deleteUser(int userID) {
        userRepository.deleteById(userID);
    }

    @Override
    public void updateUser(int userID, User userNew) {
        User userOld = userRepository.findById(userID).orElseThrow(() -> new UsernameNotFoundException("User not found using user ID " + userID));
        if(userNew.getFirstName() != null && !userNew.getFirstName().equals(userOld.getFirstName()))
            userOld.setFirstName(userNew.getFirstName());
        if(userNew.getLastName() != null && !userNew.getLastName().equals(userOld.getLastName()))
            userOld.setLastName(userNew.getLastName());
        if(userNew.getEmail() != null && !userNew.getEmail().equals(userOld.getEmail()))
            userOld.setEmail(userNew.getEmail());
        if(userNew.getRole() != null && !userNew.getRole().equals(userOld.getRole()))
            userOld.setRole(userNew.getRole());
        if(userNew.getPassword() != null && !userNew.getPassword().equals(userOld.getPassword()))
            userOld.setPassword(passwordEncoder.encode(userNew.getPassword()));
        userRepository.save(userOld);
    }
}
