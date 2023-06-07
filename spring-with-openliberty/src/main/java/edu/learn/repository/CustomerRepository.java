package edu.learn.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import edu.learn.entities.Customer;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> { }
