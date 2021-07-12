package com.project.critter.controller;

import com.project.critter.dto.ScheduleDTO;
import com.project.critter.entity.Employee;
import com.project.critter.entity.EmployeeSkill;
import com.project.critter.entity.Pet;
import com.project.critter.entity.Schedule;
import com.project.critter.service.EmployeeService;
import com.project.critter.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleService.schedulePetsWithEmployeesForActivity(
                scheduleDTO.getPetIds(), scheduleDTO.getEmployeeIds(), scheduleDTO.getActivities(), convertScheduleDtoToSchedule(scheduleDTO));
        return convertScheduleToScheduleDto(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.getAllSchedules().stream().map(s -> convertScheduleToScheduleDto(s)).collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return scheduleService.getScheduleForPet(petId)
                .stream()
                .map(s -> convertScheduleToScheduleDto(s))
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return scheduleService.getScheduleForEmployee(employeeId)
                .stream()
                .map(s -> convertScheduleToScheduleDto(s))
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleService.getScheduleForCustomer(customerId)
                .stream()
                .map(s -> convertScheduleToScheduleDto(s))
                .collect(Collectors.toList());
    }

    public ScheduleDTO convertScheduleToScheduleDto(Schedule schedule) {
        ScheduleDTO scheduleDto = new ScheduleDTO();
        scheduleDto.setEmployeeIds(schedule.getEmployees().stream().map(Employee::getId).collect(Collectors.toList()));
        scheduleDto.setPetIds(schedule.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        BeanUtils.copyProperties(schedule, scheduleDto);
        return scheduleDto;
    }

    public Schedule convertScheduleDtoToSchedule(ScheduleDTO scheduleDto) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDto, schedule);
        return schedule;
    }
}
