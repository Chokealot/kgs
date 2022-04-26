package com.chokealot.konyiguitar.order;

import com.chokealot.konyiguitar.customer.Customer;
import com.chokealot.konyiguitar.customer.CustomerEntity;
import com.chokealot.konyiguitar.guitar.Guitar;
import com.chokealot.konyiguitar.guitar.GutiarEntity;
import lombok.Data;

@Data
public class Order {

    private Long id;
    private Customer customer;
    private Guitar guitar;
    private String orderNumber;

}
