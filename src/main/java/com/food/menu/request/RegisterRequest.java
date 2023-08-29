package com.food.menu.request;

import com.food.menu.dao.models.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private boolean isDisabled;
}
