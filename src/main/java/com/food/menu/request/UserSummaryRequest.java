package com.food.menu.request;

import com.food.menu.dao.models.Role;
import lombok.Getter;

@Getter
public class UserSummaryRequest {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private Boolean isDisabled;
}
