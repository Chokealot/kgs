package com.chokealot.konyiguitar.customer;

import com.chokealot.konyiguitar.user.User;
import lombok.Data;

import javax.annotation.Nullable;

@Data
public class Customer {

    @Nullable
    private Long id;
    private String firstname;
    private String lastname;
    private String phone;
    private String address;
    private User user;

}
