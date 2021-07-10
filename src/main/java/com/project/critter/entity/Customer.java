package com.project.critter.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Nationalized;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "Customer.findAll",
                query = "select c from Customer c"
        ),
        @NamedQuery(
                name = "Customer.findById",
                query = "select c from Customer c where c.id = :id"
        ),
        @NamedQuery(
                name = "Customer.findByPet",
                query = "select p.owner from Pet p where p.id = :id"
        )
})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Pet> pets;

    @Nationalized
    private String name;
    private String phoneNumber;

    @Column(length = 500)
    private String notes;

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void addPet(Pet pet) {
        if(pets == null) {
            pets = new ArrayList<>();
        }
        pets.add(pet);
    }

}
