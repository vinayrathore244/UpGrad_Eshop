package com.upgrad.eshop.controllers;

import com.upgrad.eshop.entities.User;
import com.upgrad.eshop.exceptions.APIException;
import com.upgrad.eshop.services.UserService;
import com.upgrad.eshop.utils.APIAuthorizer;
import com.upgrad.eshop.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private APIAuthorizer apiAuthorizer;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/details")
    public User getMyDetails (@RequestHeader(Constants.JwtToken.HEADER_KEY) String jwtToken)
            throws APIException {
        logger.debug("Processing getMyRequest for token [" + jwtToken + "]");

        logger.debug("Authorizing user");
        apiAuthorizer.authorize(jwtToken);
        logger.debug("User authorized");

        return userService.getLoggedInUser();
    }
}
