package com.example.casetest.service.pet;

import com.example.casetest.dto.request.PetRequestDto;
import com.example.casetest.dto.response.PetResponseDto;
import com.example.casetest.entity.Pet;
import com.example.casetest.mapper.PetMapper;
import com.example.casetest.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetMapper petMapper;

    @Override
    public List<PetResponseDto> findPetAll() {
        List<PetResponseDto> petResponseDtos = new ArrayList<>();
        List<Pet> petList = petRepository.findAll();
        petResponseDtos = petMapper.entitiesToDtos(petList);
        return petResponseDtos;
    }

    @Override
    public Page<PetResponseDto> findAllByNamePetResponseDtoContaining(String name, Pageable pageable) {
        Page<Pet> pet = petRepository.findAllByNamePetContaining(name, pageable);
        return petMapper.entitiesToDtosPage(pet);
    }

    @Override
    public Page<PetResponseDto> findAll(Pageable pageable) {
        Page<Pet> pets = petRepository.findAllPetPage(pageable);
        return petMapper.entitiesToDtosPage(pets);
    }
    @Override
    @Transactional
    public void remove(Long id) {
        petRepository.deletePetById(id);
    }

    @Override
    public Optional<PetResponseDto> findById(Long id) {
        Optional<Pet> pet = petRepository.findById(id);
        return Optional.ofNullable(petMapper.entityToResponseDto(pet));
    }

    @Override
    public Optional<Pet> findByIdPet(Long id) {
        return petRepository.findById(id);
    }


    @Override
    public PetResponseDto addNewPet(PetRequestDto petRequestDto) {
        PetResponseDto petResponseDto = new PetResponseDto();
        Pet pet = petMapper.requestDtoToEntity(petRequestDto);
        petRepository.save(pet);
        petResponseDto = petMapper.entityToResponseDto(Optional.of(pet));
        return petResponseDto;
    }

    @Override
    public List<PetResponseDto> getAllPetByCategoryId(int id) {
        List<Pet> pets = petRepository.findAllByCategoryPetId(id);
        List<PetResponseDto> petResponseDtos = petMapper.entitiesToDtos(pets);
        return petResponseDtos;
    }
}
