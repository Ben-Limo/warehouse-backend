package com.teckmils.warehousemanagementsystem.domain.authentication.controller;

import com.teckmils.warehousemanagementsystem.domain.authentication.dto.LoginRequest;
import com.teckmils.warehousemanagementsystem.domain.authentication.dto.RegisterRequest;
import com.teckmils.warehousemanagementsystem.domain.authentication.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(final AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/api/auth/authenticate")
    public ResponseEntity<String> authenticateUser(@RequestBody final LoginRequest loginRequest) {
        return this.authenticationService.authenticateUser(loginRequest.email(), loginRequest.password());
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<String> registerUser(@RequestBody final RegisterRequest registerRequest) {
        return this.authenticationService.registerUser(registerRequest);
    }
}
