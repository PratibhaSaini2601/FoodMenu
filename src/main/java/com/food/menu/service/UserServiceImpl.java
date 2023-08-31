package com.food.menu.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.food.menu.dao.repository.UserRepository;
import com.food.menu.request.UserSummaryRequest;
import com.food.menu.response.UserResponse;
import com.food.menu.dao.models.User;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
    private final SessionFactory sessionFactory;

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

    @Override
    public List<UserResponse> getUsersSummary(UserSummaryRequest userSummaryRequest) {
        Session session = sessionFactory.openSession();
        String hql = "FROM User";
        if(userSummaryRequest.getId() != null)
            hql = hql + " WHERE id=:id";
        if(userSummaryRequest.getEmail() != null)
            hql = hql.contains("WHERE") ? hql + " AND email=:email" : hql + " WHERE email=:email";
        if(userSummaryRequest.getFirstName() != null)
            hql = hql.contains("WHERE") ? hql + " AND firstName=:firstName" : hql + " WHERE firstName=:firstName";
        if(userSummaryRequest.getLastName() != null)
            hql = hql.contains("WHERE") ? hql + " AND lastName=:lastName" : hql + " WHERE lastName=:lastName";
        if(userSummaryRequest.getRole() != null)
            hql = hql.contains("WHERE") ? hql + " AND role=:role" : hql + " WHERE role=:role";
        if(userSummaryRequest.getRole() != null)
            hql = hql.contains("WHERE") ? hql + " AND role=:role" : hql + " WHERE role=:role";
        Query query = session.createQuery(hql, User.class);

        if(userSummaryRequest.getId() != null)
            query.setParameter("id", userSummaryRequest.getId());
        if(userSummaryRequest.getEmail() != null)
            query.setParameter("email", userSummaryRequest.getEmail());
        if(userSummaryRequest.getFirstName() != null)
            query.setParameter("firstName", userSummaryRequest.getFirstName());
        if(userSummaryRequest.getLastName() != null)
            query.setParameter("lastName", userSummaryRequest.getLastName());
        if(userSummaryRequest.getRole() != null)
            query.setParameter("role", userSummaryRequest.getRole());
        List<UserResponse> userResponseList = new ArrayList<>();
        query.getResultList().forEach(u ->
            userResponseList.add(mapper.convertValue(u, UserResponse.class))
        );
        return userResponseList;
    }
}
