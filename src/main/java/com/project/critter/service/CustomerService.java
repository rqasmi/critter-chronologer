package com.project.critter.service;

import com.project.critter.entity.Customer;
import com.project.critter.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

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
        return customerRepository.getOwnerByPet(petId);
    }
}
