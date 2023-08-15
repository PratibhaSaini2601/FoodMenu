package com.wcc.cricket.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationResponse {
    public String jwtToken;
}
