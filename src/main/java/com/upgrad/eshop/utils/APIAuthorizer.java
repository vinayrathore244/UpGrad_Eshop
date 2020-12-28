package com.upgrad.eshop.utils;

import com.upgrad.eshop.exceptions.APIException;
import com.upgrad.eshop.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class APIAuthorizer {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public void authorize(String jwtToken) throws APIException {
        if (jwtToken == null || jwtToken.isBlank()) {
            throw new APIException("Please Login first to access this endpoint!");
        }

        String authority = jwtTokenProvider
                .getAuthorityFromToken(
                        jwtToken.substring(Constants.JwtToken.JWT_TOKEN_PREFIX.length())
                ).substring(5);
        if (!authority.equals(Constants.Roles.ADMIN) && !authority.equals(Constants.Roles.USER)) {
            throw new APIException("You are not authorised to access this endpoint.");
        }
    }

    public void authorizeFor(String role, String jwtToken) throws APIException {
        if (jwtToken == null || jwtToken.isBlank()) {
            throw new APIException("Please Login first to access this endpoint!");
        }

        String authority = jwtTokenProvider.getAuthorityFromToken(jwtToken.substring(7)).substring(5);
        if (!authority.equals(role)) {
            throw new APIException("You are not authorised to access this endpoint.");
        }
    }
}
