package com.projects.transactions.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.transactions.dto.AuthResponse;
import com.projects.transactions.dto.Login;
import com.projects.transactions.service.AuthenticationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody Login login){

        String token = authService.login(login);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccessToken(token);

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
