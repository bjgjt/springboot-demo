package com.lnt.demo.service.impl;

import com.lnt.demo.dto.request.AuthenticationRequest;
import com.lnt.demo.entity.User;
import com.lnt.demo.exception.AppException;
import com.lnt.demo.exception.ExceptionCode;
import com.lnt.demo.repository.UserRepository;
import com.lnt.demo.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {
    UserRepository userRepository;

    @Override
    public boolean authenticate(AuthenticationRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ExceptionCode.USER_NOT_FOUND));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        return passwordEncoder.matches(request.getPassword(), user.getPassword());
    }
}
