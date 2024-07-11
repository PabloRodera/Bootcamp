package com.prodera.CarRegistry.service.impl;

import com.prodera.CarRegistry.controller.dto.LoginRequest;
import com.prodera.CarRegistry.controller.dto.LoginResponse;
import com.prodera.CarRegistry.controller.dto.SignUpRequest;
import com.prodera.CarRegistry.repository.UserRepository;
import com.prodera.CarRegistry.repository.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthenticationServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void signup() {
        SignUpRequest signUpRequest = new SignUpRequest("Pablo", "Rodera", "pablo.rodera@example.com", "password");

        // Mock UserService's save method
        when(userService.save(any(UserEntity.class))).thenAnswer(invocation -> {
            UserEntity userEntity = invocation.getArgument(0);
            userEntity.setId(1L); // Assuming save method sets the ID
            return userEntity;
        });

        // Mock JwtService's generateToken method
        when(jwtService.generateToken(any(UserEntity.class))).thenReturn("mocked_jwt");

        LoginResponse loginResponse = authenticationService.signup(signUpRequest);

        assertEquals("mocked_jwt", loginResponse.getJwt());
        // You can add more assertions based on expected behavior
        verify(userService, times(1)).save(any(UserEntity.class));
        verify(jwtService, times(1)).generateToken(any(UserEntity.class));
    }

    @Test
    void login() {
        LoginRequest loginRequest = new LoginRequest("pablo.rodera@example.com", "password");

        // Mock AuthenticationManager's authenticate method (no return value)
        doNothing().when(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));

        // Mock UserRepository's findByEmail method
        when(userRepository.findByEmail("pablo.rodera@example.com")).thenReturn(Optional.of(new UserEntity()));

        // Mock JwtService's generateToken method
        when(jwtService.generateToken(any(UserEntity.class))).thenReturn("mocked_jwt");

        LoginResponse loginResponse = authenticationService.login(loginRequest);

        assertEquals("mocked_jwt", loginResponse.getJwt());
        // You can add more assertions based on expected behavior
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepository, times(1)).findByEmail("pablo.rodera@example.com");
        verify(jwtService, times(1)).generateToken(any(UserEntity.class));
    }
}
