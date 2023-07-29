package ru.practicum.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.service.model.Hit;
import ru.practicum.dto.StatDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticRepository extends JpaRepository<Hit, Long> {

    @Query("SELECT new ru.practicum.dto.StatDto(a.app, a.uri,  count(a.id)) " +
            "FROM Hit a WHERE a.uri IN :uris  " +
            "AND  a.created > :start AND a.created < :end " +
            "GROUP BY a.app, a.uri " +
            "ORDER BY count(a) DESC ")
    List<StatDto> findViewStatisticsWithUris(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("uris") List<String> uris);

    @Query("SELECT new ru.practicum.dto.StatDto(a.app, a.uri,  count(DISTINCT a.ip)) " +
            "FROM Hit a WHERE a.uri IN :uris  " +
            "AND  a.created > :start AND a.created < :end " +
            "GROUP BY a.app, a.uri " +
            "ORDER BY count(DISTINCT a.ip) DESC")
    List<StatDto> findViewStatisticsWithUrisAndIpIsUnique(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, List<String> uris);


    @Query("SELECT new ru.practicum.dto.StatDto(a.app, a.uri,  count(a.id)) " +
            "FROM Hit a " +
            "WHERE a.created > :start AND a.created < :end " +
            "GROUP BY a.app, a.uri " +
            "ORDER BY count(DISTINCT a.ip) DESC")
    List<StatDto> findViewStatisticsWithoutUris(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT new ru.practicum.dto.StatDto(a.app, a.uri,  count(DISTINCT a.ip))" +
            "FROM Hit a " +
            "WHERE a.created BETWEEN :start AND :end " +
            "GROUP BY a.app, a.uri " +
            "ORDER BY count(ip) DESC ")
    List<StatDto> findViewStatisticsWithoutUrisAndIsIpUnique(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}