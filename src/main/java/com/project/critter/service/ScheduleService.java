package com.project.critter.service;

import com.project.critter.entity.*;
import com.project.critter.exception.CustomerNotFoundException;
import com.project.critter.exception.EmployeeNotFoundException;
import com.project.critter.exception.PetNotFoundException;
import com.project.critter.repository.CustomerRepository;
import com.project.critter.repository.EmployeeRepository;
import com.project.critter.repository.PetRepository;
import com.project.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Schedule schedulePetsWithEmployeesForActivity(List<Long> petIds, List<Long> employeeIds, Set<EmployeeSkill> activities, Schedule schedule) {
        Schedule returnedSchedule = scheduleRepository.merge(schedule);
        List<Employee> employees = employeeIds.stream()
                .map(id -> {
                    Employee e = employeeRepository.find(id);
                    if(e == null) {
                        throw new IllegalStateException("At least one employee with the given id does not exist.");
                    }
                    return e;
                })
                .collect(Collectors.toList());
        List<Pet> pets = petIds.stream()
                .map(id -> {
                    Pet pet = petRepository.find(id);
                    if(pet == null) {
                        throw new IllegalStateException("At least one pet with the given id does not exist.");
                    }
                    return pet;
                })
                .collect(Collectors.toList());
//        if(employees.size() == 0) {
//            throw new IllegalStateException("No employees with the given ids exist.");
//        }
//        if(pets.size() == 0) {
//            throw new IllegalStateException("No pets with the given ids exist.");
//        }
        returnedSchedule.setEmployees(employees);
        returnedSchedule.setPets(pets);

        return returnedSchedule;
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.getAll();
    }

    public List<Schedule> getScheduleForPet(Long petId) {
        Pet pet = petRepository.find(petId);
        if(pet == null) {
            throw new PetNotFoundException("A pet with the given id does not exist");
        }
        return scheduleRepository.getScheduleForPet(pet);
    }

    public List<Schedule> getScheduleForEmployee(Long employeeId) {
        Employee employee = employeeRepository.find(employeeId);
        if(employee == null) {
            throw new EmployeeNotFoundException("An employee with the given id does not exist");
        }
        return scheduleRepository.getScheduleForEmployee(employee);
    }

    public List<Schedule> getScheduleForCustomer(Long customerId) {
        Customer customer = customerRepository.find(customerId);
        if(customer == null) {
            throw new CustomerNotFoundException("A customer with the given id does not exist");
        }
        return scheduleRepository.getScheduleForCustomer(customer);
    }
}
