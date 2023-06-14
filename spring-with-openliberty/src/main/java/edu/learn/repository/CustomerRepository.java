package edu.learn.repository;

import org.springframework.stereotype.Repository;

import edu.learn.entities.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class CustomerRepository {
    @PersistenceContext(unitName = "postgreDatabase")
    private EntityManager entityManager;

    public Iterable<Customer> findAll() {
        TypedQuery<Customer> customers = this.entityManager.createNamedQuery(Customer.ALL_CUSTOMER, Customer.class);
        return customers.getResultList();
    }
 }
