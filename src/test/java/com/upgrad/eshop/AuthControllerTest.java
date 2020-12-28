package com.upgrad.eshop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.upgrad.eshop.dtos.LoginRequest;
import com.upgrad.eshop.dtos.LoginResponse;
import com.upgrad.eshop.dtos.RegisterRequest;
import com.upgrad.eshop.entities.User;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import testUtils.IntegrationTestRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerTest extends IntegrationTestRunner {

    @Test
    public void testLogin() throws JsonProcessingException {
        LoginRequest loginRequest = LoginRequest.getLoginRequest("user1", "password");

        ResponseEntity<LoginResponse> responseEntity = getResponseEntity(
                "/auth/login", HttpMethod.POST, null, loginRequest, LoginResponse.class
        );
        LoginResponse loginResponse = responseEntity.getBody();
        Assertions.assertNotNull(loginResponse);
        MatcherAssert.assertThat(loginResponse.getToken(), Matchers.notNullValue());
    }

    @Test
    public void testRegister() throws JsonProcessingException {
        RegisterRequest registerRequest = new RegisterRequest("test", "password", "test",
                "test", "test@upgrad.com", "1234567890");
        ResponseEntity<User> userResponseEntity = getResponseEntity(
                "/auth/register", HttpMethod.POST, null, registerRequest, User.class);
        User user = userResponseEntity.getBody();
        Assertions.assertNotNull(user);
        MatcherAssert.assertThat(user.getUserName(), Matchers.equalTo(registerRequest.getUserName()));
        MatcherAssert.assertThat(user.getRole(), Matchers.equalTo("USER"));
    }
}
