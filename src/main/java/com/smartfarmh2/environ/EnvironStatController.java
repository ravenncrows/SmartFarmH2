package com.smartfarmh2.environ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@CrossOrigin
@Controller
public class EnvironStatController {
    @Autowired
    EnvironStatService environStatService;
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @RequestMapping(value = "/environStat",method = RequestMethod.GET)
    public @ResponseBody
    List<EnvironStat> list(){
        return environStatService.list();
    }

    @RequestMapping(value = "/environStat/today/hour/latest", method = RequestMethod.GET)
    public @ResponseBody
    EnvironStat getLatestEnvironStatOfCurrentHour(){
        EnvironStat environStat = environStatService.calculateStatOfCurrentHour();
        simpMessagingTemplate.convertAndSend("/environStat/today/hour/latest", environStat);
        return environStat;
    }

    @RequestMapping(value = "/environStat/date/{year}/{month}/{day}", method = RequestMethod.GET)
    public @ResponseBody EnvironStat getEnvironStatOfDay(@PathVariable("year") Integer year,
                                                        @PathVariable("month") Integer month,
                                                        @PathVariable("day") Integer day) {
        return environStatService.calculateStatOfDay(LocalDate.of(year,month,day));
    }

    @RequestMapping(value = "/environStat/date/{year}/{month}/{day}/{hour}", method = RequestMethod.GET)
    public @ResponseBody EnvironStat getEnvironStatOfHour(@PathVariable("year") Integer year,
                                                         @PathVariable("month") Integer month,
                                                         @PathVariable("day") Integer day,
                                                         @PathVariable("hour") Integer hour) {
        return environStatService.calculateStatOfHour(LocalDateTime.of(year,month,day,hour,0));
    }
}
