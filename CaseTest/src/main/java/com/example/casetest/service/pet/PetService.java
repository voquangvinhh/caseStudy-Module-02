package com.example.casetest.service.pet;

import com.example.casetest.dto.request.PetRequestDto;
import com.example.casetest.dto.response.PetResponseDto;
import com.example.casetest.entity.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PetService {
    List<PetResponseDto> findPetAll();
    Page<PetResponseDto> findAllByNamePetResponseDtoContaining(String name, Pageable pageable);
    Page<PetResponseDto> findAll(Pageable pageable);
    //    Page<PetResponseDto> findAllPetPage(Pageable pageable);
    void remove(Long id);
    Optional<PetResponseDto> findById(Long id);
    Optional<Pet> findByIdPet(Long id);
    PetResponseDto addNewPet(PetRequestDto petRequestDto);
    List<PetResponseDto> getAllPetByCategoryId(int id);
}
