package com.example.casetest.service.role;

import com.example.casetest.dto.RoleDto;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<RoleDto> findAll();

    Optional<RoleDto> findById(Long id);

    void save(RoleDto roleDto);

    void remove(Integer id);
}
