package com.flightbooking.domain.auth.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SessionStatus {

    ACTIVE(1),
    INACTIVE(0),
    ;

    private final int status;
}
