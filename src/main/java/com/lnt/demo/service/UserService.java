package com.lnt.demo.service;

import com.lnt.demo.dto.request.UserCreationRequest;
import com.lnt.demo.entity.User;

public interface UserService {
    User createUser(UserCreationRequest request);
}
