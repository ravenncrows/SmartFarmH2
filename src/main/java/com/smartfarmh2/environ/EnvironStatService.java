package com.smartfarmh2.environ;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EnvironStatService
{
    EnvironStat create (EnvironStat environStat);
    List<EnvironStat> list();
    EnvironStat calculateStatOfCurrentHour();
    EnvironStat calculateStatOfHour(LocalDateTime dateTime);
    EnvironStat calculateStatOfDay(LocalDate date);
    EnvironStat createEnvironStatFromEnvironList(List<Environ> environList);
}
