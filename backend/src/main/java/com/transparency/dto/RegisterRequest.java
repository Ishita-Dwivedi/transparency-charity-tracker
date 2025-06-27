package com.transparency.dto;
import jakarta.validation.constraints.NotBlank;
import com.transparency.entity.Role;

public class RegisterRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private String name;
    private Role role;
}
