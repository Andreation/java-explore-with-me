package ru.practicum.statisticservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.statisticdto.HitDto;
import ru.practicum.statisticdto.StatDto;
import ru.practicum.statisticservice.model.HitMapper;
import ru.practicum.statisticservice.repository.StatisticRepository;

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
        if (uris == null || uris.isEmpty())
            return unique ?
                    statisticRepository.getStatisticWithoutUrisWithUniqueIp(start, end) :
                    statisticRepository.getStatisticWithoutUris(start, end);
        return unique ?
                    statisticRepository.getStatisticWithUrisWithUniqueIp(start, end, uris) :
                    statisticRepository.getStatisticWithUris(start, end, uris);
    }

}