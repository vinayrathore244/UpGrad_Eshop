package com.upgrad.eshop.utils;

public final class Constants {
    private Constants() {
    }

    public static final class Roles {
        private Roles() {
        }

        public static final String USER = "USER";
        public static final String ADMIN = "ADMIN";
    }

    public static final class JwtToken {
        private JwtToken() {
        }

        public static final String HEADER_KEY = "Authorization";
        public static final String JWT_TOKEN_PREFIX = "Bearer ";
        public static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 10; // 10 minutes
        public static final String AUTHORITY_KEY = "scopes";
    }
}
