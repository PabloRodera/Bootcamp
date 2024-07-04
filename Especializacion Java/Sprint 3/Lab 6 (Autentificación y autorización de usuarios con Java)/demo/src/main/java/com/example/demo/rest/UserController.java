package com.example.demo.rest;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.SingUpRequest;
import com.example.demo.service.impl.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<?> singup(@RequestBody SingUpRequest request) {
       try {
           return ResponseEntity.ok(authenticationService.signup(request));
       }
       catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }
}
