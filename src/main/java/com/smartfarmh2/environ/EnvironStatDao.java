package com.smartfarmh2.environ;

import java.time.LocalDateTime;
import java.util.List;

public interface EnvironStatDao {
    EnvironStat create (EnvironStat environStat);
    EnvironStat update (EnvironStat environStat);
    void delete (Long id);
    EnvironStat getEnvironStat (Long id);
    List<EnvironStat> list();
    List<EnvironStat> findByCreatedAtBetween(LocalDateTime start, LocalDateTime finish);
}
