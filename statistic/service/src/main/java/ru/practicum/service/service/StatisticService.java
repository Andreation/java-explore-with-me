package ru.practicum.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.service.model.HitMapper;
import ru.practicum.dto.HitDto;
import ru.practicum.dto.StatDto;
import ru.practicum.service.repository.StatisticRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final StatisticRepository statisticRepository;

    public void createHit(HitDto hitDto) {
        statisticRepository.save(HitMapper.toDto(hitDto));
    }

    public List<StatDto> getStatistic(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        if (end.isBefore(start)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "end.isBefore(start)");
        }
        if (uris == null || uris.isEmpty())
            return unique ?
                    statisticRepository.findViewStatisticsWithoutUrisAndIsIpUnique(start, end) :
                    statisticRepository.findViewStatisticsWithoutUris(start, end);
        return unique ?
                    statisticRepository.findViewStatisticsWithUrisAndIpIsUnique(start, end, uris) :
                    statisticRepository.findViewStatisticsWithUris(start, end, uris);
    }

}