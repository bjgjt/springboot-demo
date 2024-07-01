package com.lnt.demo.service;

import com.lnt.demo.dto.request.AuthenticationRequest;

public interface AuthenticationService {
    boolean authenticate(AuthenticationRequest request);
}
