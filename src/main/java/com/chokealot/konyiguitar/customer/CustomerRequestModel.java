package com.chokealot.konyiguitar.customer;

import com.chokealot.konyiguitar.user.UserEntity;
import lombok.Data;

import javax.persistence.OneToOne;

@Data
public class CustomerRequestModel {

    private String firstname;
    private String lastname;
    private String phone;
    private String address;
    private String username;

}
