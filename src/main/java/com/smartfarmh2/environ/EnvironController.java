package com.smartfarmh2.environ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@Controller
public class EnvironController {

    @Autowired
    EnvironService environService;
    @Autowired
    SimpMessagingTemplate template;

    @RequestMapping(value = "/environ",method = RequestMethod.GET)
    public @ResponseBody List<Environ> list(){
        return environService.list();
    }

    @RequestMapping(value = "/environ",method = RequestMethod.POST)
    public @ResponseBody Environ create(@RequestBody Environ environ){
        Environ createdEnviron = environService.create(environ);
        this.template.convertAndSend("/environ/monitor",createdEnviron);
        return createdEnviron;

    }

    @RequestMapping(value = "/environ/{id}",method = RequestMethod.GET)
    public @ResponseBody Environ getProduct(@PathVariable("id") Long id){
        return environService.getEnviron(id);
    }

    @RequestMapping(value = "/environ/{id}",method = RequestMethod.PUT)
    public @ResponseBody Environ edit(@PathVariable("id") Long id, @RequestBody Environ environ){
        return environService.update(environ);
    }

    @RequestMapping(value = "/environ/{id}",method = RequestMethod.DELETE)
    public @ResponseBody void delete(@PathVariable("id") Long id){
        environService.delete(id);
    }

    @RequestMapping(value = "/environ/stats/today/{hour}", method = RequestMethod.GET)
    public @ResponseBody EnvironStat calculateStatOfHour(@PathVariable("hour") Integer hour){
        return environService.calculateStatOfHour(hour);
    }

    @RequestMapping(value = "/environ/stats/date/{year/{month}/{day}", method = RequestMethod.GET)
    public @ResponseBody EnvironStat calculateStatOfDay(@PathVariable("year") Integer year,
                                          @PathVariable("month") Integer month,
                                          @PathVariable("day") Integer day) {
        return environService.calculateStatOfDay(LocalDate.of(year,month,day));
    }

    // TODO: remove this after fix Ardruino
    @RequestMapping(value = "/environ/temp", method = RequestMethod.GET)
    public @ResponseBody Environ temporaryReceiveDataPath(@RequestParam("temp") Double temp,
                                         @RequestParam("humid") Double humid,
                                         @RequestParam("soil") Double soil){
        Environ createdEnviron = environService.create(new Environ(temp,humid,soil));
        this.template.convertAndSend("/environ/monitor",createdEnviron);
        return createdEnviron;
    }
}
