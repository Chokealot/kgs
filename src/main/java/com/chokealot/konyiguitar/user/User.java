package com.chokealot.konyiguitar.user;

import lombok.Data;
import javax.annotation.Nullable;

@Data
public class User {

    @Nullable
    private Long id;
    private String username;
    private String password;
    private String email;

}