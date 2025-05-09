package com.example.spring.auth;

import com.example.spring.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest     {
    private String username;
    private String password;
    private Role role;
}
