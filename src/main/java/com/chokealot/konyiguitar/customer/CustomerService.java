package com.chokealot.konyiguitar.customer;

import org.springframework.stereotype.Service;

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
}
