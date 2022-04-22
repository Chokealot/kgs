package com.chokealot.konyiguitar.customer;

import lombok.Data;

@Data
public class Customer {

    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private Long phone;
    private String address;
}
