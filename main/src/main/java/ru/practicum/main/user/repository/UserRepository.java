package ru.practicum.main.user.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.main.user.model.User;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select a from User a where ?1 is null or a.id in ?1")
    List<User> findByIdIn(Set<Integer> ids, Pageable pageable);

}