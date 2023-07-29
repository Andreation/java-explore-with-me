package ru.practicum.statisticservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.statisticdto.StatDto;
import ru.practicum.statisticservice.model.Hit;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticRepository extends JpaRepository<Hit, Long> {

    @Query("SELECT new ru.practicum.dto.StatDto(a.hit_app, a.hit_uri,  count(a.hit_id)) " +
            "FROM Hit a WHERE a.hit_uri IN :uris  " +
            "AND  a.timestamp > start AND a.timestamp < :end " +
            "GROUP BY a.hit_app, a.hit_uri " +
            "ORDER BY count(a) DESC ")
    List<StatDto> getStatisticWithUris(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("uris") List<String> uris);

    @Query("SELECT new ru.practicum.dto.StatDto(a.hit_app, a.hit_uri,  count(DISTINCT a.hit_ip)) " +
            "FROM Hit a WHERE a.hit_uri IN :uris  " +
            "AND  a.timestamp > start AND a.timestamp < :end " +
            "GROUP BY a.hit_app, a.hit_uri " +
            "ORDER BY count(DISTINCT a.hit_ip) DESC")
    List<StatDto> getStatisticWithUrisWithUniqueIp(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, List<String> uris);


    @Query("SELECT new ru.practicum.dto.StatDto(a.hit_app, a.hit_uri,  count(a.hit_id)) " +
            "FROM Hit a " +
            "WHERE a.timestamp > start AND a.timestamp < :end " +
            "GROUP BY a.hit_app, a.hit_uri " +
            "ORDER BY count(DISTINCT a.hit_ip) DESC")
    List<StatDto> getStatisticWithoutUris(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT new ru.practicum.dto.StatDto(a.hit_app, a.hit_uri,  count(DISTINCT a.hit_ip))" +
            "FROM Hit a " +
            "WHERE a.timestamp BETWEEN start AND :end " +
            "GROUP BY a.hit_app, a.hit_uri " +
            "ORDER BY count(hit_ip) DESC ")
    List<StatDto> getStatisticWithoutUrisWithUniqueIp(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}