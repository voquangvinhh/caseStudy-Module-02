package com.example.casetest.controller;

import com.example.casetest.dto.response.PetResponseDto;
import com.example.casetest.service.category.pet.CategoryPetService;
import com.example.casetest.service.pet.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/shop")
public class HomeController {
    @Autowired
    private CategoryPetService categoryPetService;

    @Autowired
    private PetService petService;

    @GetMapping("")
    public ModelAndView showListPet(@RequestParam(value = "search") Optional<String> search, Pageable pageable){
        Page<PetResponseDto> pets;
        if (search.isPresent()){
            pets = petService.findAllByNamePetResponseDtoContaining(search.get(), pageable);
        } else {
            pets = petService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("views/shop");
        modelAndView.addObject("pets", pets);
        modelAndView.addObject("categoriesPet", categoryPetService.getAllCategory());
        return modelAndView;
    }
    @GetMapping("/categoryPet/{id}")
    public ModelAndView shopByCategoryPet(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("views/shop");
//        modelAndView.addObject("cartCount", GlobalData.cart.size());
        modelAndView.addObject("categoriesPet", categoryPetService.getAllCategory());
        modelAndView.addObject("pets", petService.getAllPetByCategoryId(id));
//        modelAndView.addObject("products", productService.getAllProductByCategoryId(id));
        return modelAndView;
    }

    @GetMapping("/viewpet/{id}")
    public ModelAndView viewProduct(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView("views/pet");
//        modelAndView.addObject("cartCount", GlobalData.cart.size());
//        modelAndView.addObject("product", productService.getProductById(id).get());
        modelAndView.addObject("pet", petService.findById(id).get());
        return modelAndView;
    } //view product Details
}
