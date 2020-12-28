package testUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upgrad.eshop.dtos.LoginRequest;
import com.upgrad.eshop.dtos.LoginResponse;
import com.upgrad.eshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.Arrays;

public class IntegrationTestRunner {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    public ProductService productService;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    private String getApiUrl(String apiUrl) {
        return getRootUrl() + apiUrl;
    }

    public <T> ResponseEntity<T> getResponseEntity(
            String apiUrl, HttpMethod httpMethod, String token, Object requestObject, Class<T> responseType)
            throws JsonProcessingException {

        return testRestTemplate.exchange(
                getApiUrl(apiUrl), httpMethod, getHttpEntity(token, requestObject), responseType
        );
    }

    public <T> ResponseEntity<T> getResponseEntity(
            String apiUrl, HttpMethod httpMethod, String token, Object requestObject, ParameterizedTypeReference<T> responseType)
            throws JsonProcessingException {
        return testRestTemplate.exchange(
                getApiUrl(apiUrl), httpMethod, getHttpEntity(token, requestObject), responseType
        );
    }

    private HttpEntity<String> getHttpEntity(String token, Object requestObject) throws JsonProcessingException {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter =
                new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(
                MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM
        ));
        testRestTemplate.getRestTemplate().getMessageConverters().add(mappingJackson2HttpMessageConverter);

        HttpHeaders headers = new HttpHeaders();
        if (token != null && token.trim().length() > 0){
            headers.set("Authorization", "Bearer " + token);
        }
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        return new HttpEntity<>(objectMapper.writeValueAsString(requestObject), headers);
    }

    public LoginResponse performLogin(String userName, String password) {
        LoginRequest loginRequest = LoginRequest.getLoginRequest(userName, password);
        return testRestTemplate.getRestTemplate().postForEntity(
                "/auth/login", loginRequest, LoginResponse.class).getBody();
    }
}
