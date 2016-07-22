package com.smartfarmh2.environ;

import java.time.LocalDate;
import java.util.List;

public interface EnvironService {
    Environ create (Environ environ);
    Environ update (Environ environ);
    void delete (Long id);
    Environ getEnviron (Long id);
    List<Environ> list();
    Environ findNewestOne();
    EnvironStat calculateStatOfHour(Integer hour);
    EnvironStat calculateStatOfDay(LocalDate date);
}
