package com.wcc.cricket.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wcc.cricket.dao.models.Role;
import lombok.Builder;
import lombok.NoArgsConstructor;
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
