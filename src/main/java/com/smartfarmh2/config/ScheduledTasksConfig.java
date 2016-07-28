package com.smartfarmh2.config;

import com.smartfarmh2.environ.EnvironService;
import com.smartfarmh2.environ.EnvironStat;
import com.smartfarmh2.environ.EnvironStatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class ScheduledTasksConfig {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EnvironStatService environStatService;
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    // Do every hour
    @Scheduled(cron = "0 0 * * * *")
    public void calculateEnvironStatEveryHour() {
        log.info("Date time: " + LocalDateTime.now().toString());
        EnvironStat environStat = environStatService.calculateStatOfCurrentHour();
        simpMessagingTemplate.convertAndSend("/environStat/today/hour/latest", environStat);
        environStatService.create(environStat);
    }

}
