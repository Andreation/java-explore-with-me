package ru.practicum.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.dto.HitDto;



@Service
public class StatClient {

    private final RestTemplate restTemplate;

    private final String url;
    public StatClient(@Value("${statistic.url}") String url) {
        this.url = url;
        this.restTemplate = new RestTemplateBuilder()
                .uriTemplateHandler(new DefaultUriBuilderFactory(url))
                .build();
    }

    public void addHit(HitDto hitDto) {
        HttpEntity<HitDto> request = new HttpEntity<>(hitDto);
        restTemplate.postForObject("hit", request, String.class);
    }

}