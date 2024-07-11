package com.prodera.CarRegistry.controller;

import com.prodera.CarRegistry.controller.dto.LoginRequest;
import com.prodera.CarRegistry.controller.dto.SignUpRequest;
import com.prodera.CarRegistry.service.impl.AuthenticationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Mock
    private AuthenticationServiceImpl authenticationService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void signup_ValidRequest_ReturnsOkResponse() {
        SignUpRequest request = new SignUpRequest("username", "surname", "tester@example.com", "password");

        when(authenticationService.signup(any(SignUpRequest.class))).thenCallRealMethod();

        ResponseEntity<?> responseEntity = userController.singup(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("signup_successful", responseEntity.getBody());
    }

    @Test
    void signup_InvalidRequest_ReturnsBadRequestResponse() {
        SignUpRequest request = new SignUpRequest(null, "surname", "tester@example.com", null);

        doThrow(new IllegalArgumentException()).when(authenticationService).signup(any(SignUpRequest.class));

        ResponseEntity<?> responseEntity = userController.singup(request);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void login_ValidRequest_ReturnsOkResponse() {
        LoginRequest request = new LoginRequest("username", "password");

        when(authenticationService.login(any(LoginRequest.class))).thenCallRealMethod();

        ResponseEntity<?> responseEntity = userController.login(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("login_successful", responseEntity.getBody());
    }
    @Test
    void login_InvalidCredentials_ReturnsUnauthorizedResponse() {
        LoginRequest request = new LoginRequest("invalid_username", "invalid_password");

        doThrow(new IllegalArgumentException()).when(authenticationService).login(any(LoginRequest.class));

        ResponseEntity<?> responseEntity = userController.login(request);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }
}
