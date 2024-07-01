package com.lnt.demo.controller;

import com.lnt.demo.dto.ApiResponse;
import com.lnt.demo.dto.reponse.AuthenticationResponse;
import com.lnt.demo.dto.request.AuthenticationRequest;
import com.lnt.demo.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping(path = "/log-in")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        boolean result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(AuthenticationResponse.builder().authenticated(result).build())
                .build();
    }
}
