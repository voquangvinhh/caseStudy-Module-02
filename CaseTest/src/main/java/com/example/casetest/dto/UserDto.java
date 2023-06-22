package com.example.casetest.dto;

import com.example.casetest.entity.Role;
import lombok.Data;

@Data
public class UserDto {
    private Role role;
    private String account;
    private String password;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
}
