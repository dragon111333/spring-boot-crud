package com.testsdemo.testcrud.util;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.Duration;
public class RestApi {

    private RestTemplate restTemplate(){
        int timeout = 10000;
        RestTemplateBuilder builder = new RestTemplateBuilder();
        builder.setConnectTimeout(Duration.ofMillis(timeout));
        builder.setReadTimeout(Duration.ofMillis(timeout));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return builder.defaultHeader(String.valueOf(headers)).requestFactory(this::clientHttpRequestFactory).build();
        //return builder.requestFactory(() -> requestFactory).build();
    }

    public <T> T exchange(URI uri, HttpMethod httpMethod, HttpEntity<?> requestEntity, Class<T> responseType) {
        try {
            ResponseEntity<T> res = this.restTemplate().exchange(uri, httpMethod, requestEntity, responseType);
            System.out.println("successssssssssssssss");
            return res.getBody();
        } catch (RestClientResponseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        int connectTimeoutMillis = 180 * 1000; // 45 seconds in milliseconds
        factory.setConnectTimeout(connectTimeoutMillis);// 45 seconds

        // Set the read timeout to 45 seconds
        int readTimeoutMillis = 180 * 1000; // 45 seconds in milliseconds
        factory.setReadTimeout(readTimeoutMillis);// 45 seconds

        return factory;
    }
}
