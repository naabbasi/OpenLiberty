package edu.learn.services;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.learn.entities.Customer;
import edu.learn.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Iterable<Customer> all() {
        return this.customerRepository.findAll(Sort.unsorted());
    }
}
