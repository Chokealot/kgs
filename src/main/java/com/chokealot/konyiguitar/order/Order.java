package com.chokealot.konyiguitar.order;

import com.chokealot.konyiguitar.customer.CustomerEntity;
import com.chokealot.konyiguitar.guitar.GutiarEntity;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
public class Order {

    private Long id;
    private CustomerEntity customer;
    private GutiarEntity guitar;

}
