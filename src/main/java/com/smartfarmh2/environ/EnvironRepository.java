package com.smartfarmh2.environ;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EnvironRepository extends JpaRepository<Environ,Long>{
    List<Environ> findByCreatedDateBetween(LocalDateTime start, LocalDateTime finish);
}
