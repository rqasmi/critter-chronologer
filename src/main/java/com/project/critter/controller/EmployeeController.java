package com.project.critter.controller;

import com.project.critter.dto.EmployeeDTO;
import com.project.critter.dto.EmployeeRequestDTO;
import com.project.critter.entity.Employee;
import com.project.critter.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Employees.
 *
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping()
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return convertEmployeeToEmployeeDto(employeeService.addEmployee(convertEmployeeDtoToEmployee(employeeDTO)));
    }

    @PostMapping("/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return convertEmployeeToEmployeeDto(employeeService.getEmployeeById(employeeId));
    }

    @PutMapping("/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setAvailability(employeeId, daysAvailable);
    }

    @GetMapping("/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employee> employees = employeeService.findAvailableEmployeesForService(employeeDTO.getSkills(), employeeDTO.getDate().getDayOfWeek());
        return employees.stream().map(e -> convertEmployeeToEmployeeDto(e)).collect(Collectors.toList());
    }

    public EmployeeDTO convertEmployeeToEmployeeDto(Employee employee) {
        EmployeeDTO employeeDto = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDto);
        return employeeDto;
    }

    public Employee convertEmployeeDtoToEmployee(EmployeeDTO employeeDto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);
        return employee;
    }

}
