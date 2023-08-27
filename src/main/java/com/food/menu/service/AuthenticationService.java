package com.food.menu.service;

import com.food.menu.dao.repository.UserRepository;
import com.food.menu.request.AuthenticationRequest;
import com.food.menu.request.RegisterRequest;
import com.food.menu.response.AuthenticationResponse;
import com.food.menu.dao.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .jwtToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (authRequest.getEmail(), authRequest.getPassword()));
        var user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found by email " + authRequest.getEmail()));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .jwtToken(jwtToken)
                .build();
    }
}
