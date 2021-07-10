package com.project.critter.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "Schedule.findAll",
                query = "SELECT s FROM Schedule s"
        ),
        @NamedQuery(
                name = "Schedule.findForPet",
                query = "SELECT s FROM Schedule s WHERE :pet MEMBER OF s.pets"
        ),
        @NamedQuery(
                name = "Schedule.findForEmployee",
                query = "SELECT s FROM Schedule s WHERE :employee MEMBER OF s.employees"
        ),
        @NamedQuery(
                name = "Schedule.findForCustomer",
                query = "SELECT DISTINCT s FROM Schedule s JOIN s.pets p WHERE p.owner = :customer"
        )
})

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "schedule_pet",
            joinColumns = { @JoinColumn(name = "schedule_id") },
            inverseJoinColumns = { @JoinColumn(name = "pet_id") }
    )
    private List<Pet> pets;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "schedule_employee",
            joinColumns = { @JoinColumn(name = "schedule_id") },
            inverseJoinColumns = { @JoinColumn(name = "employee_id") }
    )
    private List<Employee> employees;

    public Schedule() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
