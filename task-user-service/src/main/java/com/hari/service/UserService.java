package com.hari.service;

import com.hari.model.User;

import java.util.List;

public interface UserService {
public User getUserProfile(String jwt);
public List<User> getAllUsers();
}
