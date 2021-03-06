package com.chokealot.konyiguitar.customer;

import com.chokealot.konyiguitar.user.User;
import lombok.Data;

@Data
public class Customer {

    private Long id;
    private String firstname;
    private String lastname;
    private String phone;
    private String address;
    private User user;

}
