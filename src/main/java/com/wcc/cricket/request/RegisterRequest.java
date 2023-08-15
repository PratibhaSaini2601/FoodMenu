package com.wcc.cricket.request;

import com.wcc.cricket.user.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public Role role;
}
