package com.food.menu.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.food.menu.dao.models.Role;
import lombok.Setter;

@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {
    public int Id;
    public String firstName;
    public String lastName;
    public String email;
    public Role role;
    public Boolean isDisabled;
}
