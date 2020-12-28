package com.upgrad.eshop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.upgrad.eshop.dtos.ShippingAddressRequest;
import com.upgrad.eshop.entities.ShippingAddress;
import com.upgrad.eshop.exceptions.APIException;
import com.upgrad.eshop.security.JwtTokenProvider;
import com.upgrad.eshop.utils.APIAuthorizer;
import com.upgrad.eshop.validators.ShippingAddressValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import testUtils.IntegrationTestRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class ShippingAddressControllerTest extends IntegrationTestRunner {
    @SpyBean
    private ShippingAddressValidator shippingAddressValidator;

    @SpyBean
    private APIAuthorizer apiAuthorizer;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Test
    public void testAddService() throws APIException, JsonProcessingException {
        Mockito.doNothing().when(apiAuthorizer).authorizeFor(Mockito.anyString(), Mockito.anyString());
        Mockito.doNothing().when(apiAuthorizer).authorize(Mockito.anyString());
        Mockito.doNothing().when(shippingAddressValidator).validateAddAddress(Mockito.any());
        Mockito.when(jwtTokenProvider.getUsernameFromToken(Mockito.anyString())).thenReturn("user1");
        Mockito.when(jwtTokenProvider.validateToken(Mockito.anyString(), Mockito.any())).thenReturn(true);
        String token = "token";
        ShippingAddressRequest shippingAddressRequest = new ShippingAddressRequest(
                "test name",
                "test phone",
                "test street",
                "test landmark",
                "test city",
                "test state",
                "test zip"
        );
        ResponseEntity<ShippingAddress> responseEntity = getResponseEntity(
                "/user-addresses", HttpMethod.POST, token, shippingAddressRequest, ShippingAddress.class
        );
        ShippingAddress shippingAddress = responseEntity.getBody();
        Assertions.assertNotNull(shippingAddress);
        Assertions.assertEquals(shippingAddressRequest.getName(), shippingAddress.getName());
    }
}
