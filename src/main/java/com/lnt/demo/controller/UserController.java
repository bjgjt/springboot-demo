package com.lnt.demo.controller;

import com.lnt.demo.dto.request.UserCreationRequest;
import com.lnt.demo.dto.request.UserUpdateRequest;
import com.lnt.demo.entity.User;
import com.lnt.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody UserCreationRequest request) {
        return userService.createUser(request);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "/{userId}")
    public User getUser(@PathVariable(name = "userId", required = true) String userId) {
        return userService.getUser(userId);
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
