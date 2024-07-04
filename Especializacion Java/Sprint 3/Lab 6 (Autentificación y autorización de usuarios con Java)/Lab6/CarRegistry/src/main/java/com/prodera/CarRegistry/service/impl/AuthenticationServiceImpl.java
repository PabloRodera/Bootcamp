package com.prodera.CarRegistry.service.impl;

import com.prodera.CarRegistry.controller.dto.LoginRequest;
import com.prodera.CarRegistry.controller.dto.LoginResponse;
import com.prodera.CarRegistry.controller.dto.SignUpRequest;
import com.prodera.CarRegistry.repository.UserRepository;
import com.prodera.CarRegistry.repository.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl {

    private final UserRepository userRepository;
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginResponse signup(SignUpRequest request) throws BadRequestException {
        var user = UserEntity
                .builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("ROLE_CLIENT") //El rol por defecto es CLIENT como se indica
                .build();

        userService.save(user);
        var jwt = jwtService.generateToken(user);
        return LoginResponse.builder().jwt(jwt).build();
    }

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUser(), request.getPassword()));
        var user = userRepository.findByEmail(request.getUser()).orElseThrow(() -> new IllegalArgumentException("Invalid user or password"));

        var jwt = jwtService.generateToken(user);
        return LoginResponse.builder().jwt(jwt).build();
    }

}
