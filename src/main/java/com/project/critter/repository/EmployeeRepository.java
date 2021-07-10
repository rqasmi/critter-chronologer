package com.project.critter.repository;

import com.project.critter.entity.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.DayOfWeek;
import java.util.List;

@Transactional
@Repository
public class EmployeeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Employee employee) {
        entityManager.persist(employee);
    }

    public Employee find(Long id) {
        return entityManager.find(Employee.class, id);
    }

    public Employee merge(Employee employee) {
        return entityManager.merge(employee);
    }

    public void delete(Long id) {
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
    }

    public List<Employee> getAllEmployees() {
        TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findAll", Employee.class);
        return query.getResultList();
    }

    public List<Employee> findAvailableEmployeesForService(DayOfWeek dayOfWeek) {
        TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findEmployeesForService", Employee.class);
        query.setParameter("dayOfWeek", dayOfWeek);
        return query.getResultList();
    }


}
