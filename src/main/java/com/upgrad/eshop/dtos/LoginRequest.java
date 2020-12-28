package com.upgrad.eshop.dtos;

import lombok.Data;

@Data
public class LoginRequest {
    private String userName;
    private String password;

    public static LoginRequest getLoginRequest(String userName, String password) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserName(userName);
        loginRequest.setPassword(password);
        return loginRequest;
    }
}
