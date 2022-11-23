package com.chokealot.konyiguitar.customer;

import com.chokealot.konyiguitar.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerRequestModel model) {
        try {
            Customer customerToCreate = service.createCustomer(model);
            logger.info("A customer has been created");
            return ResponseEntity.ok(customerToCreate);
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<Customer> getCustomerByName(@PathVariable String name) {
        try {
            Customer customer = service.getCustomerByName(name);
            logger.info("Getting a customer by name");
            return ResponseEntity.ok(customer);
        } catch (IllegalArgumentException e) {
            logger.warn("Error while trying to get a customer by name");
            logger.warn(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        try {
            Customer customer = service.getCustomerById(id);
            logger.info("found Customer");
            return ResponseEntity.ok(customer);
        } catch (IllegalArgumentException e) {
            logger.warn("Error while trying to get a customer by id");
            logger.warn(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<Stream<Customer>> getAllCustomers() {
        try {
            logger.info("Getting all customers");
            return ResponseEntity.ok(service.getAllCustomers());
        } catch (IllegalArgumentException e) {
            logger.warn("Error while trying to get all customers");
            logger.warn(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        try {
            service.deleteCustomerById(id);
            logger.info("Customer has been deleted");
            return ResponseEntity.ok("Deleted");
        } catch (IllegalArgumentException e) {
            logger.warn("No customer to delete with that id");
            logger.warn(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
