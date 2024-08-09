package com.flightbooking.domain.auth.service;

import com.flightbooking.domain.auth.dto.TokenResponse;
import com.flightbooking.domain.auth.entity.User;

public class TokenService {

    public static TokenResponse generateToken(User user) {
        return TokenResponse.builder()
                .accessToken("testAccessToken")
                .expiresIn(1000L)
                .build();
    }
}
