package com.example.system_t.users.Dtos;

import com.example.system_t.users.UserRole;

public record RegisterDTO(UserRole role, String login, String password) {
}
