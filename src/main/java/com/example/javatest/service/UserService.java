package com.example.javatest.service;

import java.util.UUID;

import com.example.javatest.model.User;
import com.example.javatest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        user.setId(UUID.randomUUID());
        return userRepository.save(user);
    }
}