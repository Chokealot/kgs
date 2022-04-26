package com.chokealot.konyiguitar.customer;

import org.springframework.stereotype.Service;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class CustomerService {

    CustomerRepository repository;
    CustomerMapper mapper;

    public CustomerService(CustomerRepository repository, CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Customer createCustomer(Customer customer) {
        return mapper.toDTO(repository.save(mapper.fromDTO(customer)));
    }

    public Customer getCustomerByName(String name) {
        Customer customer = mapper.toDTO(repository.findByFirstname(name));
        return customer;
    }

    public Customer getCustomerById(Long id) {
        Customer customer = mapper.toDTO(repository.findById(id).get());
        return customer;
    }

    public Stream<Customer> getAllCustomers() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::toDTO);
    }

    public Customer deleteCustomerById(Long id) {
        CustomerEntity customerToDelete = repository.findById(id).get();
        repository.delete(customerToDelete);
        return mapper.toDTO(customerToDelete);
    }
}
