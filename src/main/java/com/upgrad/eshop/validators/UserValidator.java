package com.upgrad.eshop.validators;

import com.upgrad.eshop.dtos.LoginRequest;
import com.upgrad.eshop.dtos.RegisterRequest;
import com.upgrad.eshop.exceptions.APIException;

public interface UserValidator {
    public void validateUserRegistration(RegisterRequest registerRequest) throws APIException;
    public void validateUserLogin(LoginRequest loginRequest) throws APIException;
}
