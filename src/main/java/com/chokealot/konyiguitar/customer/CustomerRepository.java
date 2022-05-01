package com.chokealot.konyiguitar.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity findByFirstname(String name);

    CustomerEntity findCustomerEntitiesById(Long Id);

}
