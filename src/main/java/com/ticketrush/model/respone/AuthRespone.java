package com.ticketrush.model.respone;

import java.util.List;

public class AuthRespone {
    private String access_token;
    private String refresh_token;
    private String token_type = "Bearer";
    private long expires_in;
    List<String> roles;

    public String getAccess_token() {
        return access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
