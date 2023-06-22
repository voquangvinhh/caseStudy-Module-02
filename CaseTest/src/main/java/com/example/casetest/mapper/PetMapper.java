package com.example.casetest.mapper;

import com.example.casetest.dto.request.PetRequestDto;
import com.example.casetest.dto.response.PetResponseDto;
import com.example.casetest.entity.Pet;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PetMapper {
    public List<PetResponseDto> entitiesToDtos(List<Pet> pets){
        List<PetResponseDto> petResponseDtos = new ArrayList<>();
        for (Pet pet : pets){
            PetResponseDto petResponseDto = entityToResponseDto(Optional.ofNullable(pet));
            petResponseDtos.add(petResponseDto);
        }
        return petResponseDtos;
    }

    public Page<PetResponseDto> entitiesToDtosPage(Page<Pet> pets){
        List<PetResponseDto> petResponseDtos = new ArrayList<>();
        for (Pet pet : pets){
            PetResponseDto petResponseDto = entityToResponseDto(Optional.ofNullable(pet));
            BeanUtils.copyProperties(pet, petResponseDto);
            petResponseDtos.add(petResponseDto);
        }
        return new PageImpl<>(petResponseDtos, pets.getPageable(), pets.getTotalElements());
    }

    public PetResponseDto entityToResponseDto(Optional<Pet> pet) {
        PetResponseDto petResponseDto = new PetResponseDto();
        BeanUtils.copyProperties(pet.get(), petResponseDto);
        return petResponseDto;
    }

    public Pet requestDtoToEntity(PetRequestDto petRequestDto){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petRequestDto, pet);
        return pet;
    }
}
