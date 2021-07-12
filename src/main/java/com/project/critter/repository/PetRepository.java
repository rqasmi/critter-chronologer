package com.project.critter.repository;

import com.project.critter.entity.Customer;
import com.project.critter.entity.Pet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PetRepository {
    @PersistenceContext
    EntityManager entityManager;

    public Pet persist(Pet pet) {
        entityManager.persist(pet);
        return pet;
    }

    public Pet find(Long id) {
        return entityManager.find(Pet.class, id);
    }

    public Pet merge(Pet pet) {
        return entityManager.merge(pet);
    }

    public void delete(Long id) {
        Pet pet = entityManager.find(Pet.class, id);
        entityManager.remove(pet);
    }

    public List<Pet> getAllPets() {
        TypedQuery<Pet> query = entityManager.createNamedQuery("Pet.findAll", Pet.class);
        return query.getResultList();
    }

    public List<Pet> getPetsByOwner(Customer owner) {
        TypedQuery<Pet> query = entityManager.createNamedQuery("Pet.getPetsByOwner", Pet.class);
        query.setParameter("owner", owner);
        return query.getResultList();
    }

}
