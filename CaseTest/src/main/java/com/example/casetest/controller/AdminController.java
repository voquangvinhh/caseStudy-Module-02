package com.example.casetest.controller;

import com.example.casetest.dto.request.PetRequestDto;
import com.example.casetest.dto.response.PetResponseDto;
import com.example.casetest.entity.CategoryPet;
import com.example.casetest.service.category.pet.CategoryPetService;
import com.example.casetest.service.pet.PetService;
import com.example.casetest.service.role.RoleService;
import com.example.casetest.service.user.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @ModelAttribute("species")
    public Iterable<CategoryPet> species() {
        return categoryPetService.getAllCategory();
    }

    private final PetService petService;
    private final CategoryPetService categoryPetService;
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(PetService petService, UserService userService, RoleService roleService, CategoryPetService categoryPetService) {
        this.petService = petService;
        this.categoryPetService = categoryPetService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String admin() {
        return "admin/adminHome";
    }

    @GetMapping("/users")
    public String getAcc(Model model) {
        model.addAttribute("users");
        return null;
    }

    @GetMapping("pet")
    public ModelAndView showAll(@PageableDefault(sort = "price",direction = Sort.Direction.DESC) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("pet/list");
        modelAndView.addObject("pets", petService.findAll(pageable));
        return modelAndView;
    }

    @GetMapping("create-pet")
    public ModelAndView createPet(@ModelAttribute("pet") PetRequestDto petRequestDto) {
        ModelAndView modelAndView = new ModelAndView("pet/create");
        modelAndView.addObject("pets", new PetRequestDto());
        return modelAndView;
    }

    @PostMapping("/create-pet")
    public ModelAndView savePet(@ModelAttribute("pet") PetRequestDto petRequestDto) {
        ModelAndView modelAndView = new ModelAndView("pet/create");
        modelAndView.addObject("pets", petService.addNewPet(petRequestDto));
        modelAndView.addObject("message", "New pet created successfully");
        return modelAndView;
    }

    @GetMapping("/pet/delete/{id}")
    public ModelAndView deletePet(@PathVariable Long id) {
        Optional<PetResponseDto> pet = petService.findById(id);
        ModelAndView modelAndView;
        if (pet.isPresent()) {
            modelAndView = new ModelAndView("pet/delete");
            modelAndView.addObject("pet", pet.get());
        } else {
            modelAndView = new ModelAndView("/error.404");
        }
        return modelAndView;
    }

    @PostMapping("/delete-pet")
    public String delete(@ModelAttribute("pet") Optional<PetRequestDto> pet) {
        petService.remove(pet.get().getId());
        return "redirect:/admin/pet";
    }

    @GetMapping("pet/edit/{id}")
    public ModelAndView editPet(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("pet/edit");
        modelAndView.addObject("pet", petService.findById(id).get());
        return modelAndView;
    }

    @PostMapping("/pet/edit")
    public ModelAndView editCustomer(@ModelAttribute("pet") PetRequestDto petRequestDto){
        petService.addNewPet(petRequestDto);
        ModelAndView modelAndView = new ModelAndView("/pet/edit");
        modelAndView.addObject("message", "Edit Pet Successfully !");
        return modelAndView;
    }
}
