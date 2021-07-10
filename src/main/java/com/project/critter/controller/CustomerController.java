package com.project.critter.controller;

import com.project.critter.dto.CustomerDTO;
import com.project.critter.entity.Customer;
import com.project.critter.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    /**
     * Creates a customer
     * @param customerDTO
     * @return updated CustomerDTO
     */
    @PostMapping()
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return convertCustomerToCustomerDTO(customerService.addCustomer(convertCustomerDTOToCustomer(customerDTO)));
    }

    @GetMapping()
    public List<CustomerDTO> getAllCustomers(){
        return customerService.listAllCustomers().stream().map(c -> convertCustomerToCustomerDTO(c)).collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        return convertCustomerToCustomerDTO(customerService.getOwnerByPet(petId));
    }

    /**
     * Convert CustomerDTO to Customer
     * @param customerDto
     * @return converted customer
     */
    public Customer convertCustomerDTOToCustomer(CustomerDTO customerDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        return customer;
    }

    /**
     * Convert Customer to CustomerDTO
     * @param customer
     * @return converted customerDTO
     */
    public CustomerDTO convertCustomerToCustomerDTO(Customer customer) {
        CustomerDTO customerDto = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDto);
        if(customer.getPets() != null) {
            customerDto.setPetIds(customer.getPets().stream().map(p -> p.getId()).collect(Collectors.toList()));
        }
        return customerDto;
    }
}
