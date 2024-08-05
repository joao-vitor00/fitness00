package com.jvsc.Request;

public class AccessDto {

    private String token;

    public AccessDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
