package com.chokealot.konyiguitar.order;

import com.chokealot.konyiguitar.customer.Customer;
import com.chokealot.konyiguitar.customer.CustomerEntity;
import com.chokealot.konyiguitar.guitar.GutiarEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "order")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CustomerEntity customer;

    @ManyToOne
    private GutiarEntity guitar;

}
