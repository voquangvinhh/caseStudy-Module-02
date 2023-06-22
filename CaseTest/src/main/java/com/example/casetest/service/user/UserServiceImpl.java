package com.example.casetest.service.user;

import com.example.casetest.dto.RoleDto;
import com.example.casetest.dto.UserDto;
import com.example.casetest.entity.User;
import com.example.casetest.mapper.UserMapper;
import com.example.casetest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<UserDto> findAll() {
        List<UserDto> userDtos = new ArrayList<>();
        List<User> users = userRepository.findAll();
        userDtos = userMapper.entitiesToDtos(users);
        return userDtos;
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return Optional.ofNullable(userMapper.entityToDto(user));
    }

    @Override
    public void save(UserDto userDto) {
        User user = userMapper.dtoToEntity(userDto);
        if (!userDto.getPassword().isEmpty()) {
            String hashedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(10));
            user.setPassword(hashedPassword);
        }
        userRepository.save(user);
    }



    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> findAllByRole(RoleDto roleDto) {
        List<UserDto> userDtos = new ArrayList<>();
        List<User> entities = userRepository.findAllByRole(roleDto);
        userDtos = userMapper.entitiesToDtos(entities);
        return userDtos;
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        Page<UserDto> userDtos = userMapper.entitiesToDtosPage(users);
        return userDtos;
    }

    @Override
    public Page<UserDto> findAllByFullNameContaining(String firstname, Pageable pageable) {
        Page<User> users = userRepository.findAllByFullNameContaining(firstname, pageable);
        return userMapper.entitiesToDtosPage(users);
    }

    @Override
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null
                || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }
}
