package com.lnt.demo.service.impl;

import com.lnt.demo.dto.reponse.AuthenticationResponse;
import com.lnt.demo.dto.request.AuthenticationRequest;
import com.lnt.demo.entity.User;
import com.lnt.demo.exception.AppException;
import com.lnt.demo.exception.ExceptionCode;
import com.lnt.demo.repository.UserRepository;
import com.lnt.demo.service.AuthenticationService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {
    UserRepository userRepository;

    @NonFinal
    protected static String SIGNER_KEY = "HLfV6RF+EMyOmZK4hK9v4D6hwoxJD8DShoMBKV1h5gdm75zrnvIzhoWRqiWfXA9v";

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) throws JOSEException {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ExceptionCode.USER_NOT_FOUND));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated) throw new AppException(ExceptionCode.UNAUTHENTICATED);

        var token = generateToken(user.getUsername());

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    private String generateToken(String username) throws JOSEException {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("bookstore-project")
                .issueTime(new Date())
                .expirationTime(
                        new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli())
                )
                .claim("customClaim", "Custom")
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
        return jwsObject.serialize();
    }
}
