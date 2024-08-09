package com.flightbooking.domain.auth.service;

import com.flightbooking.app.constant.ResponseCode;
import com.flightbooking.app.dto.exception.BusinessException;
import com.flightbooking.domain.auth.dto.TokenResponse;
import com.flightbooking.domain.auth.dto.request.LoginRequest;
import com.flightbooking.domain.auth.entity.User;
import com.flightbooking.domain.auth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;


    public TokenResponse authenticate(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        if (user == null) {
            throw new BusinessException(ResponseCode.USERNAME_OR_PASSWORD_INVALID);
        }

        if (!user.getPassword().equals(request.getPassword())) {
            throw new BusinessException(ResponseCode.USERNAME_OR_PASSWORD_INVALID);
        }

        TokenResponse response = TokenService.generateToken(user);

        return response;
    }
}

