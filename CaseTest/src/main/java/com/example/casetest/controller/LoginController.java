package com.example.casetest.controller;

import com.example.casetest.dto.RoleDto;
import com.example.casetest.dto.UserDto;
import com.example.casetest.entity.Role;
import com.example.casetest.entity.User;
import com.example.casetest.mapper.RoleMapper;
import com.example.casetest.repository.RoleRepository;
import com.example.casetest.repository.UserRepository;
import com.example.casetest.service.role.RoleService;
import com.example.casetest.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LoginController {


    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMapper roleMapper;

    @ModelAttribute("roleDtos")
    public List<RoleDto> roleDtos(){
        return roleService.findAll();
    }


    @GetMapping("/login")
    public String login(){
        return "views/login";
    }

    @GetMapping("/register")
    public ModelAndView registerForm(){
        ModelAndView modelAndView = new ModelAndView("views/register");
        modelAndView.addObject("userDto", new UserDto());
        return modelAndView;
    } //page register

    @PostMapping("/register")
    public String saveUser(@ModelAttribute("userDto") UserDto userDto ) {
        RoleDto roleDto = roleService.findById(2L).orElseThrow(() -> new RuntimeException("Role not found"));
        Role role = roleMapper.dtoToEntity(roleDto);
        userDto.setRole(role);
        System.out.println("asd");
        userService.save(userDto);
        return "redirect:/login";
    }
}
