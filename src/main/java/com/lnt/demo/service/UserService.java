package com.lnt.demo.service;

import com.lnt.demo.dto.reponse.UserResponse;
import com.lnt.demo.dto.request.UserCreationRequest;
import com.lnt.demo.dto.request.UserUpdateRequest;
import com.lnt.demo.entity.User;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserCreationRequest request);
    List<UserResponse> getUsers();
    UserResponse getUser(String userId);
    User updateUser(String userId, UserUpdateRequest request);
    void deleteUser(String userId);
}
