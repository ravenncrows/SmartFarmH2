package com.smartfarmh2.environ;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EnvironStatRepository extends JpaRepository<EnvironStat, Long> {
    List<EnvironStat> findByCreatedAtBetween(LocalDateTime start, LocalDateTime finish);
}
