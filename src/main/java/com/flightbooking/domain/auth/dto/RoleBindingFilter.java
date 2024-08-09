package com.flightbooking.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RoleBindingFilter {
    private String userId;
    private Integer roleId;
    private String roleName;
}
