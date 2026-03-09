package com.ticketrush.model.respone;

import lombok.Data;

import java.util.List;

@Data
public class AuthRespone {
    private String access_token;
    private String token_type = "Bearer";
    private long expires_in;
    private List<String> roles;
}
