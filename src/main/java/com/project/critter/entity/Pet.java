package com.project.critter.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "Pet.findAll",
                query = "SELECT p FROM Pet p"
        ),
        @NamedQuery(
                name = "Pet.findById",
                query = "SELECT p FROM Pet p WHERE p.id = :id"
        ),
        @NamedQuery(
                name = "Pet.getPetsByOwner",
                query = "SELECT p FROM Pet p WHERE p.owner = :owner"
        )

})

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private PetType type;
    private String name;

    private LocalDate birthDate;

    @Nationalized
    @Column(length = 500)
    private String notes;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Customer owner;

    @ManyToMany(mappedBy = "pets")
    private List<Schedule> schedules;

    public Pet() {
    }

    public Pet(PetType type, String name, Customer owner, LocalDate birthDate, String notes) {
        this.type = type;
        this.name = name;
        this.owner = owner;
        this.birthDate = birthDate;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
