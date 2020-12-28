package com.upgrad.eshop.controllers;

import com.upgrad.eshop.dtos.LoginRequest;
import com.upgrad.eshop.dtos.LoginResponse;
import com.upgrad.eshop.dtos.RegisterRequest;
import com.upgrad.eshop.entities.User;
import com.upgrad.eshop.exceptions.*;
import com.upgrad.eshop.security.JwtTokenProvider;
import com.upgrad.eshop.services.UserService;
import com.upgrad.eshop.validators.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    /*
     * Credentials for ADMIN
     * username: admin
     * email: admin@upgrad.com
     * password: password
     */
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest)
            throws APIException, BadCredentialsException {
        logger.debug("Processing login request from " + loginRequest.getUserName());

        logger.debug("Started login request validation for " + loginRequest.getUserName());
        userValidator.validateUserLogin(loginRequest);
        logger.debug("Complete login request validation for " + loginRequest.getUserName());

        logger.debug("Started authentication for " + loginRequest.getUserName());
        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUserName(),
                            loginRequest.getPassword()
                    )
            );
            logger.debug("Authentication successful.");

            logger.debug("Generating Jwt token for " + loginRequest.getUserName());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = jwtTokenProvider.generateToken(authentication);
            logger.debug("Jwt Token generated successfully. Token [" + token + "]");

            final LoginResponse loginResponse = new LoginResponse(loginRequest.getUserName(), "Success", token);
            return ResponseEntity.ok(loginResponse);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid Credentials!");
        }
    }

    /*
     * User will be registered with role USER.
     */
    @PostMapping("/register")
    public User saveUser(@RequestBody RegisterRequest registerRequest)
            throws APIException, UsernameAlreadyRegisteredException, EmailAlreadyRegisteredException {
        logger.debug("Processing register request from " + registerRequest.getUserName());

        logger.debug("Started register request validation for " + registerRequest.getUserName());
        userValidator.validateUserRegistration(registerRequest);
        logger.debug("Completed register request validation for " + registerRequest.getUserName());

        return userService.acceptUserDetails(registerRequest);
    }
}
