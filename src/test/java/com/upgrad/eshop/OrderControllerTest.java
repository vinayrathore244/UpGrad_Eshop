package com.upgrad.eshop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.upgrad.eshop.dtos.OrderRequest;
import com.upgrad.eshop.entities.Order;
import com.upgrad.eshop.exceptions.APIException;
import com.upgrad.eshop.security.JwtTokenProvider;
import com.upgrad.eshop.utils.APIAuthorizer;
import com.upgrad.eshop.validators.OrderValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import testUtils.IntegrationTestRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTest extends IntegrationTestRunner {

    @SpyBean
    private OrderValidator orderValidator;

    @SpyBean
    private APIAuthorizer apiAuthorizer;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Test
    public void testCreateOrder() throws JsonProcessingException, APIException {
        Mockito.doNothing().when(apiAuthorizer).authorizeFor(Mockito.anyString(), Mockito.anyString());
        Mockito.doNothing().when(apiAuthorizer).authorize(Mockito.anyString());
        Mockito.doNothing().when(orderValidator).validateOrderRequest(Mockito.any());
        Mockito.when(jwtTokenProvider.getUsernameFromToken(Mockito.anyString())).thenReturn("user1");
        Mockito.when(jwtTokenProvider.validateToken(Mockito.anyString(), Mockito.any())).thenReturn(true);
        OrderRequest orderRequest = new OrderRequest(50L, 4L);
        String token = "token";
        ResponseEntity<Order> responseEntity = getResponseEntity(
                "/orders", HttpMethod.POST, token, orderRequest, Order.class
        );
        Order order = responseEntity.getBody();
        Assertions.assertNotNull(order);
        Assertions.assertNotNull(order.getId());
    }
}
