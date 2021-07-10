package com.project.critter.controller;

import com.project.critter.dto.PetDTO;
import com.project.critter.entity.Pet;
import com.project.critter.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping()
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        return convertPetToPetDto(petService.save(petDTO.getOwnerId(), convertPetDtoToPet(petDTO)));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return convertPetToPetDto(petService.findPetById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return petService.getAllPets().stream().map(p -> convertPetToPetDto(p)).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return petService.getPetsByOwner(ownerId).stream().map(p -> convertPetToPetDto(p)).collect(Collectors.toList());
    }

    private Pet convertPetDtoToPet(PetDTO petDto) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDto, pet);
        return pet;
    }

    private PetDTO convertPetToPetDto(Pet pet) {
        PetDTO petDto = new PetDTO();
        BeanUtils.copyProperties(pet, petDto);
        petDto.setOwnerId(pet.getOwner().getId());
        return petDto;
    }
}
