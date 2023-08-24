package com.wcc.cricket.service;

import com.wcc.cricket.dao.models.User;
import com.wcc.cricket.dao.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserByID(int userID) {
        return userRepository.findById(userID).orElseThrow(() -> new UsernameNotFoundException("User not found using user ID " + userID));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
