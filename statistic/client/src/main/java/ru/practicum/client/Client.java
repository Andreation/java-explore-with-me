package ru.practicum.client;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Client {
    private final RestTemplate rest;

    public Client(RestTemplate rest) {
        this.rest = rest;
    }

    protected  <T> ResponseEntity<Object> post(String path, T body) {
        HttpEntity<Object> requestEntity = new HttpEntity<>(body);
        ResponseEntity<Object> statServerResponse;
        statServerResponse = rest.exchange(path, HttpMethod.POST, requestEntity, Object.class);
        return prepareResponse(statServerResponse);
    }

    protected ResponseEntity<Object> get(String path, Map<String, Object> parameters) {
        HttpEntity<Object> requestEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<Object> statServerResponse;
        statServerResponse = rest.exchange(path, HttpMethod.GET, requestEntity, Object.class, parameters);
        return prepareResponse(statServerResponse);
    }

    private static ResponseEntity<Object> prepareResponse(ResponseEntity<Object> response) {
        if (response.getStatusCode().is2xxSuccessful())
            return response;
        if (response.hasBody())
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        return ResponseEntity.status(response.getStatusCode()).build();
    }

}