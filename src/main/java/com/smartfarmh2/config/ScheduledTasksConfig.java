package com.smartfarmh2.config;

import com.smartfarmh2.device.DeviceService;
import com.smartfarmh2.device.DeviceSettingService;
import com.smartfarmh2.environ.EnvironService;
import com.smartfarmh2.environ.EnvironStat;
import com.smartfarmh2.environ.EnvironStatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import Microgear.Microgear;

@Component
public class ScheduledTasksConfig {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Value("${netpie.appId}")
    private String netPieAppId;
    @Value("${netpie.key}")
    private String netPieKey;
    @Value("${netpie.secret}")
    private String netPieSecret;

    @Autowired
    DeviceService deviceService;

    @Autowired
    EnvironStatService environStatService;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    DeviceSettingService deviceSettingService;

    // Do every hour
    @Scheduled(cron = "0 0 * * * *")
    public void calculateEnvironStatEveryHour() {
        log.info("Date time: " + LocalDateTime.now().toString());
        EnvironStat environStat = environStatService.calculateStatOfCurrentHour();
        simpMessagingTemplate.convertAndSend("/environStat/today/hour/latest", environStat);
        //Save statistics
        environStatService.create(environStat);
    }

    // Do every 30s
    @Scheduled(fixedDelay=30000)
    public void calculateEnvironStatEveryMinute() {
        log.info("Date time: " + LocalDateTime.now().toString());
        EnvironStat environStat = environStatService.calculateStatOfCurrentHour();
        simpMessagingTemplate.convertAndSend("/environStat/today/hour/latest", environStat);

        // command device to turn on/off water if threshold is met
        Microgear microgear =  new Microgear();
        microgear.connect(netPieAppId,netPieKey,netPieSecret);
        microgear.Publish("/waterThreshold","on");
        deviceSettingService.list().forEach(deviceSetting -> {

            boolean isMetThreshold = deviceSetting.getWaterThreshold() < environStat.getAverageHumid();
            //check threshold
            if(isMetThreshold){
                microgear.Publish("/waterThreshold","on");
            }
            else {
                microgear.Publish("/waterThreshold","off");
            }
        });

    }

}
