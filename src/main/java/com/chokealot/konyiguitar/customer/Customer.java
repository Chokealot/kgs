package com.chokealot.konyiguitar.customer;

import lombok.Data;

@Data
public class Customer {

    private Long id;
    private String firstname;
    private String lastname;
    private Long phone;
    private String address;

}
