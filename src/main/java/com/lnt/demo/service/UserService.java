package com.lnt.demo.service;

import com.lnt.demo.dto.request.UserCreationRequest;
import com.lnt.demo.dto.request.UserUpdateRequest;
import com.lnt.demo.entity.User;

import java.util.List;

public interface UserService {
    User createUser(UserCreationRequest request);
    List<User> getUsers();
    User getUser(String userId);
    User updateUser(String userId, UserUpdateRequest request);
    void deleteUser(String userId);
}
