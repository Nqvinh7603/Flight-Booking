package com.flightbooking.app.api;

import com.flightbooking.domain.auth.dto.TokenResponse;
import com.flightbooking.domain.auth.dto.request.LoginRequest;
import com.flightbooking.domain.auth.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class UserController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {
        return authenticationService.authenticate(request);
    }
}