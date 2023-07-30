package ru.practicum.service.model;

import ru.practicum.dto.HitDto;

public final class HitMapper {

    public static Hit toDto(HitDto hitDto) {
        return Hit.builder()
                .app(hitDto.getApp())
                .ip(hitDto.getIp())
                .uri(hitDto.getUri())
                .created(hitDto.getCreated())
                .build();
    }
}