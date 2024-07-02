package com.lnt.demo.service;

import com.lnt.demo.dto.reponse.AuthenticationResponse;
import com.lnt.demo.dto.reponse.IntrospectResponse;
import com.lnt.demo.dto.request.AuthenticationRequest;
import com.lnt.demo.dto.request.IntrospectRequest;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request) throws JOSEException;
    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;
}
