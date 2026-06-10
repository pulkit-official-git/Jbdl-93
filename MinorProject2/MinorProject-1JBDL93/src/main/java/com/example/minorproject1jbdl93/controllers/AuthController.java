package com.example.minorproject1jbdl93.controllers;

import com.example.minorproject1jbdl93.dtos.LoginRequest;
import com.example.minorproject1jbdl93.dtos.LoginResponse;
import com.example.minorproject1jbdl93.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        String token = jwtService.generateToken((UserDetails) authentication.getPrincipal());

        return LoginResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .expiresInMillis(jwtService.getJwtExpirationMillis())
                .build();
    }
}
