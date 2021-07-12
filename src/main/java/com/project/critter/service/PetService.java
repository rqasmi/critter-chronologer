package com.project.critter.service;

import com.project.critter.entity.Customer;
import com.project.critter.entity.Pet;
import com.project.critter.exception.CustomerNotFoundException;
import com.project.critter.repository.CustomerRepository;
import com.project.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Pet save(Long ownerId, Pet pet) {
        Pet returnedPet = petRepository.merge(pet);
        Customer owner = customerRepository.find(ownerId);
        returnedPet.setOwner(owner);
        owner.addPet(returnedPet);
        return returnedPet;
    }

    public Pet findPetById(Long id) {
        return petRepository.find(id);
    }

    public List<Pet> getAllPets() {
        return petRepository.getAllPets();
    }

    public List<Pet> getPetsByOwner(Long ownerId) {
        Customer owner = customerRepository.find(ownerId);
        if(owner == null)
            throw new CustomerNotFoundException("An owner with the given id does not exist");
        return petRepository.getPetsByOwner(owner);
    }


}
