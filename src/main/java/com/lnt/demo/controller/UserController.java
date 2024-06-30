package com.lnt.demo.controller;

import com.lnt.demo.dto.ApiResponse;
import com.lnt.demo.dto.reponse.UserResponse;
import com.lnt.demo.dto.request.UserCreationRequest;
import com.lnt.demo.dto.request.UserUpdateRequest;
import com.lnt.demo.entity.User;
import com.lnt.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setResult(userService.createUser(request));
        response.setCode(0);
        return response;
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers() {
        ApiResponse<List<UserResponse>> response = new ApiResponse<>();
        response.setResult(userService.getUsers());
        response.setCode(0);
        return response;
    }

    @GetMapping(path = "/{userId}")
    public ApiResponse<UserResponse> getUser(@PathVariable(name = "userId", required = true) String userId) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(userId))
                .build();
    }

    @PutMapping(path = "/{userId}")
    public User updateUser(@PathVariable(name = "userId", required = true) String userId,
                           @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping(path = "/{userId}")
    public void deleteUser(@PathVariable(name = "userId", required = true) String userId) {
        userService.deleteUser(userId);
    }
}
