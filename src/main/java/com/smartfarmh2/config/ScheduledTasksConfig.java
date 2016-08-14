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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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

    // http sender
    private RestTemplate restTemplate;
    private HttpHeaders headers;

    public ScheduledTasksConfig() {
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", netPieKey+":"+netPieSecret);
    }

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
        deviceSettingService.list().forEach(deviceSetting -> {
            String deviceName = deviceService.getDevice(deviceSetting.getDevice().getId()).getName();
            boolean isMetThreshold = deviceSetting.getWaterThreshold() < environStat.getAverageSoil();
            //check threshold
            if(isMetThreshold){
                turnWaterSwitch(deviceName,"on");
            }
            else {
                turnWaterSwitch(deviceName,"off");
            }
        });
    }
    private void turnWaterSwitch(String deviceName, String status){
        try {
            String netPiePath = "https://api.netpie.io/microgear/" + netPieAppId + "/" + deviceName + "?retain";
            restTemplate.exchange(netPiePath, HttpMethod.GET, new HttpEntity<Object>(headers), String.class);
        }
        catch (Exception e) {
            log.error("turnWaterSwitch: " + status + " has error");
            e.printStackTrace();
        }
    }
}
