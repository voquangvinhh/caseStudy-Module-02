package com.example.casetest.dto;


import lombok.Data;

import java.util.List;

@Data
public class RoleDto {
    private Long id;
    private String name;
    private String desc;
    List<UserDto> userDtos;
}
