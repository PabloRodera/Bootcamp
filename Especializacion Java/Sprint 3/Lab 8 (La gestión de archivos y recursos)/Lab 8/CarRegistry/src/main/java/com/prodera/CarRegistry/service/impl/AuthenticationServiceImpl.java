package com.prodera.CarRegistry.service.impl;

import com.prodera.CarRegistry.controller.dto.LoginRequest;
import com.prodera.CarRegistry.controller.dto.LoginResponse;
import com.prodera.CarRegistry.controller.dto.SignUpRequest;
import com.prodera.CarRegistry.repository.UserRepository;
import com.prodera.CarRegistry.repository.entity.UserEntity;
import com.prodera.CarRegistry.service.AuthenticationService;
import com.prodera.CarRegistry.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginResponse signup(SignUpRequest request){
        var user = UserEntity
                .builder()
                .name(request.getName())
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
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return LoginResponse.builder().jwt(jwt).build();
    }
}
