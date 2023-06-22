package com.example.casetest.mapper;

import com.example.casetest.dto.RoleDto;
import com.example.casetest.dto.UserDto;
import com.example.casetest.entity.Role;
import com.example.casetest.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RoleMapper {
    public List<RoleDto> entitiesToDtos(List<Role> roles){
        List<RoleDto> roleDtos = new ArrayList<>();
        for (Role role : roles){
            RoleDto roleDto = entityToDto(Optional.ofNullable(role));
            roleDtos.add(roleDto);
        }
        return roleDtos;
    }

    public Page<RoleDto> entitiesToDtosPage(Page<Role> roles){
        List<RoleDto> roleDtos = new ArrayList<>();
        for (Role role : roles){
            RoleDto roleDto = entityToDto(Optional.ofNullable(role));
            BeanUtils.copyProperties(role, roleDto);
            roleDtos.add(roleDto);
        }
        return new PageImpl<>(roleDtos, roles.getPageable(), roles.getTotalElements());
    }

    public RoleDto entityToDto(Optional<Role> role) {
        RoleDto roleDto = new RoleDto();
        BeanUtils.copyProperties(role.get(), roleDto);
        return roleDto;
    }

    public Role dtoToEntity(RoleDto roleDto){
        Role role = new Role();
        BeanUtils.copyProperties(roleDto, role);
        return role;
    }
}
