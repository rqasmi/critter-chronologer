package com.project.critter.service;

import com.project.critter.entity.Customer;
import com.project.critter.entity.Pet;
import com.project.critter.exception.CustomerNotFoundException;
import com.project.critter.exception.PetNotFoundException;
import com.project.critter.repository.CustomerRepository;
import com.project.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetRepository petRepository;

    public List<Customer> listAllCustomers() {
        return customerRepository.listAllCustomers();
    }

    public Customer findById(Long id) {
        return customerRepository.find(id);
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.merge(customer);
    }

    public Customer getOwnerByPet(Long petId) {
        Pet pet = petRepository.find(petId);
        if(pet == null)
            throw new PetNotFoundException("A pet with the given id does not exist");
        Customer customer = customerRepository.getOwnerByPet(pet);
        return customer;
    }
}
