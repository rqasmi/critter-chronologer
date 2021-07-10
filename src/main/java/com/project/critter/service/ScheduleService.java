package com.project.critter.service;

import com.project.critter.entity.*;
import com.project.critter.repository.CustomerRepository;
import com.project.critter.repository.EmployeeRepository;
import com.project.critter.repository.PetRepository;
import com.project.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
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
                .map(id -> employeeRepository.find(id))
//                .filter(e -> e.getSkills().containsAll(activities) && e.getDaysAvailable().contains(schedule.getDate().getDayOfWeek()))
                .collect(Collectors.toList());
        if(employees.size() < 0) {
            throw new IllegalStateException("No employees with the required skills on the given day are available.");
        } else {
            returnedSchedule.setEmployees(employees);
        }
        returnedSchedule.setPets(petIds.stream().map(id -> petRepository.find(id)).collect(Collectors.toList()));

        return returnedSchedule;
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.getAll();
    }

    public List<Schedule> getScheduleForPet(Long petId) {
        Pet pet = petRepository.find(petId);
        if(pet == null) {
            throw new IllegalArgumentException("A pet with the given id does not exist");
        }
        return scheduleRepository.getScheduleForPet(pet);
    }

    public List<Schedule> getScheduleForEmployee(Long employeeId) {
        Employee employee = employeeRepository.find(employeeId);
        if(employee == null) {
            throw new IllegalArgumentException("An employee with the given id does not exist");
        }
        return scheduleRepository.getScheduleForEmployee(employee);
    }

    public List<Schedule> getScheduleForCustomer(Long customerId) {
        Customer customer = customerRepository.find(customerId);
        if(customer == null) {
            throw new IllegalArgumentException("A customer with the given id does not exist");
        }
        return scheduleRepository.getScheduleForCustomer(customer);
    }
}
