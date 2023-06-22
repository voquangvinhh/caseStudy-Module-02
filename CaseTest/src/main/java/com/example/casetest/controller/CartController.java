package com.example.casetest.controller;

import com.example.casetest.entity.Pet;
import com.example.casetest.global.GlobalData;
import com.example.casetest.mapper.PetMapper;
import com.example.casetest.service.pet.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    @Autowired
    private PetService petService;
    @Autowired
    private PetMapper petMapper;

    @GetMapping("/cart")
    public String cartGet(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Pet::getPrice).sum());
        model.addAttribute("cart", GlobalData.cart);
        return "views/cart";
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable Long id){
        GlobalData.cart.add(petService.findByIdPet(id).get());
        return "redirect:/shop";
    }

    @GetMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable int index){
        GlobalData.cart.remove(index);
        return "redirect:/cart";
    }
}
