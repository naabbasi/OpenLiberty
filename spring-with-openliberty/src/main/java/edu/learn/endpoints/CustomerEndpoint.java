package edu.learn.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.learn.entities.Customer;
import edu.learn.services.CustomerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerEndpoint {
    private final CustomerService customerService;
    
    @GetMapping
    public Iterable<Customer> all() {
        return this.customerService.all();
    }
}
