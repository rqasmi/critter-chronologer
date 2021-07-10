package com.project.critter.entity;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@NamedQueries({
        @NamedQuery(
                name = "Employee.findAll",
                query = "SELECT e FROM Employee e"
        ),
        @NamedQuery(
                name = "Employee.findById",
                query = "SELECT e FROM Employee e WHERE e.id = :id"
        ),
        @NamedQuery(
                name = "Employee.findEmployeesForService",
                query = "SELECT e FROM Employee e WHERE :dayOfWeek MEMBER OF e.daysAvailable"
        )
})

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection(targetClass = EmployeeSkill.class)
    @JoinTable(name = "skill_tbl", joinColumns = { @JoinColumn (name = "employee_id")})
    @Column(name = "skill", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;

    @ElementCollection(targetClass = DayOfWeek.class)
    @JoinTable(name = "availability_tbl", joinColumns = { @JoinColumn (name = "employee_id")})
    @Column(name = "availability", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;

    @ManyToMany(mappedBy = "employees")
    private List<Schedule> schedules;

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> employeeSkillSet) {
        this.skills = employeeSkillSet;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> weekDays) {
        this.daysAvailable = weekDays;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
