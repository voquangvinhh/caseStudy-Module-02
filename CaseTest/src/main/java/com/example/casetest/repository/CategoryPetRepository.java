package com.example.casetest.repository;

import com.example.casetest.entity.CategoryPet;
import com.example.casetest.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryPetRepository extends JpaRepository<CategoryPet, Integer> {
}
