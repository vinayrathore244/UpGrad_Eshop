package com.upgrad.eshop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.upgrad.eshop.dtos.LoginResponse;
import com.upgrad.eshop.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import testUtils.IntegrationTestRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class UserControllerTest extends IntegrationTestRunner {

    @Test
    public void testGetMyDetails() throws JsonProcessingException {
        LoginResponse loginResponse = performLogin("user1", "password");
        ResponseEntity<User> responseEntity = getResponseEntity(
                "/users/details", HttpMethod.GET, loginResponse.getToken(), null, User.class
        );
        User user = responseEntity.getBody();
        Assertions.assertNotNull(user);
        Assertions.assertEquals("user1", user.getUserName());
    }
}
