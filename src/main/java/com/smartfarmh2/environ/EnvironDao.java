package com.smartfarmh2.environ;

import com.smartfarmh2.environ.Environ;

import java.time.LocalDateTime;
import java.util.List;

public interface EnvironDao {
    Environ create (Environ environ);
    Environ update (Environ environ);
    void delete (Long id);
    Environ getEnviron (Long id);
    List<Environ> list();
    List<Environ> findByCreatedDateBetween(LocalDateTime start, LocalDateTime finish);
}
