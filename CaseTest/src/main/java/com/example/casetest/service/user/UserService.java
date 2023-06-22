package com.example.casetest.service.user;

import com.example.casetest.dto.RoleDto;
import com.example.casetest.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> findAll();

    Optional<UserDto> findById(Long id);

    void save(UserDto userDto);

    void remove(Long id);
    List<UserDto> findAllByRole(RoleDto roleDto);
    Page<UserDto> findAll(Pageable pageable);
    Page<UserDto> findAllByFullNameContaining(String fullName, Pageable pageable);

    boolean isAuthenticated();
}
