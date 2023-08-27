package com.food.menu.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.food.menu.dao.models.Role;
import lombok.Setter;

@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {
    public int ID;
    public String firstName;
    public String lastName;
    public String email;
    public Role role;
}
