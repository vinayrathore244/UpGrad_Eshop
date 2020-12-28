package com.upgrad.eshop.validators;

import com.upgrad.eshop.daos.UserRepository;
import com.upgrad.eshop.dtos.LoginRequest;
import com.upgrad.eshop.dtos.RegisterRequest;
import com.upgrad.eshop.exceptions.APIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserValidatorImpl implements UserValidator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void validateUserRegistration(RegisterRequest registerRequest) throws APIException {
        if (registerRequest.getUserName() == null || registerRequest.getUserName().length() <= 0) {
            throw new APIException("Invalid Username");
        }

        if (registerRequest.getFirstName() == null || registerRequest.getFirstName().length() <= 0) {
            throw new APIException("Invalid FirstName");
        }

        if (registerRequest.getEmail() == null ||
                !Pattern.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-z]{2,6}$", registerRequest.getEmail())) {
            throw new APIException("Invalid email-id format!");
        }

        if (registerRequest.getPhoneNumber() == null ||
                !Pattern.matches("^\\d{10}$", registerRequest.getPhoneNumber())) {
            throw new APIException("Invalid contact number!");
        }

        if (registerRequest.getPassword() == null ||
                registerRequest.getPassword().length() < 8) {
            throw new APIException("Weak password!");
        }
    }

    @Override
    public void validateUserLogin(LoginRequest loginRequest) throws APIException {
        if (loginRequest.getUserName() == null || loginRequest.getUserName().length() <= 0) {
            throw new APIException("Invalid Username");
        }

        if (userRepository.findByUserName(loginRequest.getUserName()).isEmpty()) {
            throw new APIException("This username has not been registered!");
        }

        if (loginRequest.getPassword() == null || loginRequest.getPassword().length() < 8) {
            throw new APIException("Invalid Password");
        }
    }
}
