package ru.practicum.service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.service.service.StatisticService;
import ru.practicum.dto.HitDto;
import ru.practicum.dto.StatDto;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StatisticController {
    private final StatisticService statisticService;

    @GetMapping("/stats")
    @ResponseStatus(HttpStatus.OK)
    public List<StatDto> getStatistics(@RequestParam LocalDateTime start,
                                          @RequestParam LocalDateTime end,
                                          @RequestParam(required = false) List<String> uris,
                                          @RequestParam(defaultValue = "false") Boolean unique) {
        log.debug("GET request to /stats with parameters: start = " + start +
                ", end = " + end +  ", uris = " + uris + ", unique = " + unique);
        return statisticService.getStatistic(start, end, uris, unique);
    }

    @PostMapping("/hit")
    public ResponseEntity<Object> createHit(@RequestBody @Valid HitDto hitDto) {
        log.debug("POST request to /hit with HitDto = " + hitDto);
        statisticService.createHit(hitDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}