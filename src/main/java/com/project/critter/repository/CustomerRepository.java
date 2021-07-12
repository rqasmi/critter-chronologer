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
public class CustomerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Customer persist(Customer customer) {
        entityManager.persist(customer);
        return customer;
    }

    public Customer find(Long id) {
        return entityManager.find(Customer.class, id);
    }

    public Customer merge(Customer customer) {
        return entityManager.merge(customer);
    }


    public List<Customer> listAllCustomers() {
        TypedQuery<Customer> query = entityManager.createNamedQuery("Customer.findAll", Customer.class);
        return query.getResultList();
    }

    public Customer getOwnerByPet(Pet pet) {
        TypedQuery<Customer> query = entityManager.createNamedQuery("Customer.findByPet", Customer.class);
        query.setParameter("p", pet);
        return query.getSingleResult();
    }
}
