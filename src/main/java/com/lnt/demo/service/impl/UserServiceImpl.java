package com.lnt.demo.service.impl;

import com.lnt.demo.dto.reponse.UserResponse;
import com.lnt.demo.dto.request.UserCreationRequest;
import com.lnt.demo.dto.request.UserUpdateRequest;
import com.lnt.demo.entity.User;
import com.lnt.demo.exception.AppException;
import com.lnt.demo.exception.ExceptionCode;
import com.lnt.demo.mapper.UserMapper;
import com.lnt.demo.repository.UserRepository;
import com.lnt.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ExceptionCode.USER_EXISTED);
        }

        User user = userMapper.toUserEntity(request);
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    @Override
    public List<UserResponse> getUsers() {
        List<User> listUsers = userRepository.findAll();
        return userMapper.toListUserResponses(listUsers);
    }

    @Override
    public UserResponse getUser(String userId) {
        return userMapper.toUserResponse(userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ExceptionCode.USER_NOT_FOUND)));
    }

    @Override
    public User updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ExceptionCode.USER_NOT_FOUND));
        userMapper.updateUser(user, request);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
