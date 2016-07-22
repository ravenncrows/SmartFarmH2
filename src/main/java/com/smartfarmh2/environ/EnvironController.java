package com.smartfarmh2.environ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
public class EnvironController {

    @Autowired
    EnvironService environService;

    @RequestMapping(value = "/environ",method = RequestMethod.GET)
    public List<Environ> list(){
        return environService.list();
    }

    @RequestMapping(value = "/environ",method = RequestMethod.POST)
    public Environ create(@RequestBody Environ environ){
        return environService.create(environ);
    }

    @RequestMapping(value = "/environ/{id}",method = RequestMethod.GET)
    public Environ getProduct(@PathVariable("id") Long id){
        return environService.getEnviron(id);
    }

    @RequestMapping(value = "/environ/{id}",method = RequestMethod.PUT)
    public Environ edit(@PathVariable("id") Long id, @RequestBody Environ environ){
        return environService.update(environ);
    }

    @RequestMapping(value = "/environ/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id){
        environService.delete(id);
    }

    @RequestMapping(value = "/environ/stats/today/{hour}", method = RequestMethod.GET)
    public EnvironStat calculateStatOfHour(@PathVariable("hour") Integer hour){
        return environService.calculateStatOfHour(hour);
    }

    @RequestMapping(value = "/environ/stats/date/{year/{month}/{day}", method = RequestMethod.GET)
    public EnvironStat calculateStatOfDay(@PathVariable("year") Integer year,
                                          @PathVariable("month") Integer month,
                                          @PathVariable("day") Integer day) {
        return environService.calculateStatOfDay(LocalDate.of(year,month,day));
    }
}
