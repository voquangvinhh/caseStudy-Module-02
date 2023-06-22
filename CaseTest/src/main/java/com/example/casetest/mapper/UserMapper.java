package com.example.casetest.mapper;

import com.example.casetest.dto.UserDto;
import com.example.casetest.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserMapper {
    public List<UserDto> entitiesToDtos(List<User> users){
        List<UserDto> userDtos = new ArrayList<>();
        for (User user1 : users){
            UserDto userDto = entityToDto(Optional.ofNullable(user1));
            userDtos.add(userDto);
        }
        return userDtos;
    }

    public Page<UserDto> entitiesToDtosPage(Page<User> users){
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users){
            UserDto userDto = entityToDto(Optional.ofNullable(user));
            BeanUtils.copyProperties(user, userDto);
            userDtos.add(userDto);
        }
        return new PageImpl<>(userDtos, users.getPageable(), users.getTotalElements());
    }

    public UserDto entityToDto(Optional<User> user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user.get(), userDto);
        return userDto;
    }

    public User dtoToEntity(UserDto userDto){
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }
}
