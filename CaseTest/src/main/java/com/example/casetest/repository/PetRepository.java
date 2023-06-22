package com.example.casetest.repository;

import com.example.casetest.dto.response.PetResponseDto;
import com.example.casetest.entity.CategoryPet;
import com.example.casetest.entity.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findAllByCategoryPetId(int id);
    @Query(value = "select * from pet where pet.is_status = true and pet.name_pet like %:name% ", nativeQuery = true)
    Page<Pet> findAllByNamePetContaining(String name, Pageable pageable);
    @Query(value = "select c from Pet c where c.isStatus = true")
    Page<Pet> findAllPetPage(Pageable pageable);
    @Modifying
    @Query(value = "update Pet c set c.isStatus = false where c.id =:id")
    void deletePetById(Long id);
}
