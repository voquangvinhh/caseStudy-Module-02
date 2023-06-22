package com.example.casetest.service.category.pet;

import com.example.casetest.entity.CategoryPet;
import com.example.casetest.entity.Pet;
import com.example.casetest.repository.CategoryPetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryPetServiceImpl implements CategoryPetService {
    @Autowired
    private CategoryPetRepository categoryPetRepository;
    @Override
    public List<CategoryPet> getAllCategory() {
        return categoryPetRepository.findAll();
    }

    @Override
    public void updateCategory(CategoryPet categoryPet) {

    }

    @Override
    public void removeCategoryById(int id) {

    }

    @Override
    public Optional<CategoryPet> getCategoryById(int id) {
        return Optional.empty();
    }
}
