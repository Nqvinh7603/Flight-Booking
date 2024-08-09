package com.flightbooking.domain.auth.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public enum RoleBindingStatus {
    ACTIVE(1),
    INACTIVE(0);

    @JsonValue
    public Integer toInteger() {
        return value;
    }

    private final Integer value;

    private static Map<Integer, RoleBindingStatus> maps = new HashMap<>();

    static {
        for(RoleBindingStatus transactionStatus: RoleBindingStatus.values()){
            maps.put(transactionStatus.value, transactionStatus);
        }
    }

    @JsonCreator
    public static RoleBindingStatus fromValue(Integer value){
        return maps.getOrDefault(value, null);
    }

}

