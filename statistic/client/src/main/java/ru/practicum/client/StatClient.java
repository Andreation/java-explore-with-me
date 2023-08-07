package ru.practicum.client;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.practicum.dto.HitDto;



@Component
public class StatClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public void addHit(HitDto hitDto) {
        HttpEntity<HitDto> request = new HttpEntity<>(hitDto);
        restTemplate.postForObject("http://stats-server:9090/hit", request, String.class);
    }

}