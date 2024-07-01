package com.lnt.demo.service;

import com.lnt.demo.dto.reponse.AuthenticationResponse;
import com.lnt.demo.dto.request.AuthenticationRequest;
import com.nimbusds.jose.JOSEException;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request) throws JOSEException;
}
