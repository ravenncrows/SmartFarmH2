package com.smartfarmh2.config;

import com.smartfarmh2.environ.EnvironService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class ScheduledTasksConfig {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EnvironService environService;

    // Do every hour
    @Scheduled(cron = "0 0 * * * *")
    public void calculateEnvironStatEveryHour() {
        log.info("The time is now " + dateFormat.format(new Date()) + " start calculating environ data");
        environService.calculateStatOfHour(LocalDateTime.now().getHour());
        //TODO : add EnvironStat to db
    }

}
