package com.project.critter.service;

import com.project.critter.entity.Employee;
import com.project.critter.entity.EmployeeSkill;
import com.project.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee getEmployeeById(Long id) {
        return employeeRepository.find(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.merge(employee);
    }

    public void setAvailability(Long employeeId, Set<DayOfWeek> daysAvailable) {
        Employee employee = employeeRepository.find(employeeId);
        employee.setDaysAvailable(daysAvailable);
    }

    public List<Employee> findAvailableEmployeesForService(Set<EmployeeSkill> skills, DayOfWeek day) {
        List<Employee> employees = employeeRepository.findAvailableEmployeesForService(day);
        return employees.stream().filter(e -> e.getSkills().containsAll(skills)).collect(Collectors.toList());
    }
}
