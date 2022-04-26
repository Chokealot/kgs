package com.chokealot.konyiguitar.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.stream.Stream;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(Logger.class);

    private CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer customerToCreate = null;
        try {
            customerToCreate = service.createCustomer(customer);
            logger.info("A customer has been created");
        } catch (IllegalArgumentException e) {
            new ResponseEntity("", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.created(URI.create("api/v1/customer"+customerToCreate.getId())).build();
    }

    @GetMapping("{name}")
    public ResponseEntity<Customer> getCustomerByName(@PathVariable String name) {
        logger.info("Get one customer");
        return ResponseEntity.ok(service.getCustomerByName(name));
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        logger.info("found Customer");
        return ResponseEntity.ok(service.getCustomerById(id));
    }

    @GetMapping("")
    public ResponseEntity<Stream<Customer>> getAllCustomers() {
        logger.info("Getting all customers");
        return ResponseEntity.ok(service.getAllCustomers());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        service.deleteCustomerById(id);
        logger.info("Deleted customer with id["+id+"]");
        return ResponseEntity.ok("Deleted customer with id["+id+"]");
    }
}
