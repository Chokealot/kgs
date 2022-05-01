package com.chokealot.konyiguitar.customer;

import com.chokealot.konyiguitar.user.UserEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private String phone;
    private String address;

    @OneToOne
    @JoinColumn(name = "users_id")
    private UserEntity user;
}
