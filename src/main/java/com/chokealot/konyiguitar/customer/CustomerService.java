package com.chokealot.konyiguitar.customer;

import com.chokealot.konyiguitar.user.User;
import com.chokealot.konyiguitar.user.UserMapper;
import com.chokealot.konyiguitar.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class CustomerService {

    CustomerRepository repository;
    CustomerMapper mapper;
    UserService userService;
    UserMapper userMapper;

    public CustomerService(CustomerRepository repository, CustomerMapper mapper, UserService userService, UserMapper userMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Transactional
    public Customer createCustomer(CustomerRequestModel model) {
        CustomerEntity entity = mapper.fromDTO(convertModelToCustomer(model));
        entity.setUser(userMapper.fromDTO(userService.getUser(model.getUsername())));
        repository.save(entity);
        return mapper.toDTO(entity);
    }

    public Customer getCustomerByName(String name) {
        Customer customer = mapper.toDTO(repository.findByFirstname(name));
        return customer;
    }

    public Customer getCustomerById(Long id) {
        Customer customer = mapper.toDTO(repository.findCustomerEntitiesById(id));
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

    public Customer convertModelToCustomer(CustomerRequestModel model) {
        Customer customer = new Customer();
        customer.setFirstname(model.getFirstname());
        customer.setLastname(model.getLastname());
        customer.setPhone(model.getPhone());
        customer.setAddress(model.getAddress());
        return customer;
    }
}
