package com.hari.service.impl;

import com.hari.config.JwtProvider;
import com.hari.model.User;
import com.hari.repository.UserRepository;
import com.hari.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User getUserProfile(String jwt) {
       String email= JwtProvider.getEmailFromToken(jwt);
       return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
