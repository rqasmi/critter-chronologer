package com.project.critter.repository;

import com.project.critter.entity.Customer;
import com.project.critter.entity.Employee;
import com.project.critter.entity.Pet;
import com.project.critter.entity.Schedule;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ScheduleRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Schedule merge(Schedule schedule) {
        return entityManager.merge(schedule);
    }

    public List<Schedule> getAll() {
        TypedQuery<Schedule> query = entityManager.createNamedQuery("Schedule.findAll", Schedule.class);
        return query.getResultList();
    }

    public List<Schedule> getScheduleForPet(Pet pet) {
        TypedQuery<Schedule> query = entityManager.createNamedQuery("Schedule.findForPet", Schedule.class);
        query.setParameter("pet", pet);
        return query.getResultList();
    }

    public List<Schedule> getScheduleForEmployee(Employee employee) {
        TypedQuery<Schedule> query = entityManager.createNamedQuery("Schedule.findForEmployee", Schedule.class);
        query.setParameter("employee", employee);
        return query.getResultList();
    }

    public List<Schedule> getScheduleForCustomer(Customer customer) {
        TypedQuery<Schedule> query = entityManager.createNamedQuery("Schedule.findForCustomer", Schedule.class);
        query.setParameter("customer", customer);
        return query.getResultList();
    }
}
