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

    @Autowired
    private UserService userService;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerRequestModel model) {
        Customer customerToCreate = new Customer();
        try {
            customerToCreate = service.createCustomer(model);
            logger.info("A customer has been created");
            return ResponseEntity.ok(customerToCreate);
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<Customer> getCustomerByName(@PathVariable String name) {
        logger.info("Get one customer");
        return ResponseEntity.ok(service.getCustomerByName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        logger.info("found Customer");
        return ResponseEntity.ok(service.getCustomerById(id));
    }

    @GetMapping("")
    public ResponseEntity<Stream<Customer>> getAllCustomers() {
        logger.info("Getting all customers");
        return ResponseEntity.ok(service.getAllCustomers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        service.deleteCustomerById(id);
        logger.info("Deleted customer with id["+id+"]");
        return ResponseEntity.ok("Deleted customer with id["+id+"]");
    }
}
