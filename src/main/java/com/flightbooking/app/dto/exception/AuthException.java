package com.flightbooking.app.dto.exception;

import com.flightbooking.app.constant.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthException extends RuntimeException {
    protected ResponseCode responseCode;
}
