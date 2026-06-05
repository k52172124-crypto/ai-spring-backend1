package com.sesac.aibackend.dto;

import com.sesac.aibackend.domain.Role;
import com.sesac.aibackend.domain.User;

public record UserResponse(Long id, String username, Role role) {

    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getRole());
    }
}


