package com.example.casetest.service.role;

import com.example.casetest.dto.RoleDto;
import com.example.casetest.entity.Role;
import com.example.casetest.mapper.RoleMapper;
import com.example.casetest.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<RoleDto> findAll() {
        List<RoleDto> roleDtos = new ArrayList<>();
        List<Role> roles = roleRepository.findAll();
        roleDtos = roleMapper.entitiesToDtos(roles);
        return roleDtos;
    }

    @Override
    public Optional<RoleDto> findById(Long id) {
        return Optional.ofNullable(roleMapper.entityToDto(roleRepository.findById(id)));
    }

    @Override
    public void save(RoleDto roleDto) {

    }

    @Override
    public void remove(Integer id) {

    }
}
