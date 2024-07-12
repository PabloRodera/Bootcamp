package com.prodera.CarRegistry.service;

import com.prodera.CarRegistry.controller.dto.LoginRequest;
import com.prodera.CarRegistry.controller.dto.LoginResponse;
import com.prodera.CarRegistry.controller.dto.SignUpRequest;

public interface AuthenticationService {
    public LoginResponse signup(SignUpRequest request) throws Exception;
    public LoginResponse login(LoginRequest request) throws Exception;
}
