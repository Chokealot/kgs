package com.chokealot.konyiguitar.order;

import com.chokealot.konyiguitar.customer.Customer;
import com.chokealot.konyiguitar.customer.CustomerEntity;
import com.chokealot.konyiguitar.guitar.GutiarEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customers_id")
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guitars_id")
    private GutiarEntity guitar;

    private String orderNumber;

}
