package com.smartfarmh2.environ;

import com.smartfarmh2.device.Device;
import com.smartfarmh2.device.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
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
    DeviceService deviceService;
    @Autowired
    SimpMessagingTemplate template;

    @RequestMapping(value = "/environ",method = RequestMethod.GET)
    public @ResponseBody List<Environ> list(){
        return environService.list();
    }

    @RequestMapping(value = "/environ",method = RequestMethod.POST)
    public @ResponseBody Environ create(@RequestBody Environ environ, @RequestBody Device device){
        environ.setDevice(device);
        Environ createdEnviron = environService.create(environ);
        this.template.convertAndSend("/environ/monitor",createdEnviron);
        return createdEnviron;
    }

    @RequestMapping(value = "/environ/{id}",method = RequestMethod.GET)
    public @ResponseBody Environ getEnviron(@PathVariable("id") Long id){
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

    // TODO: remove this after fix Ardruino
    @RequestMapping(value = "/environ/temp", method = RequestMethod.GET)
    public @ResponseBody Environ temporaryReceiveDataPath(@RequestParam("temp") Double temp,
                                         @RequestParam("humid") Double humid,
                                         @RequestParam("soil") Double soil,
                                         @RequestParam("device") String deviceName){
        Device device = deviceService.findOneByName(deviceName);
        if(device == null) throw new RuntimeException("Device not found");
        Environ environ = new Environ(temp,humid,soil);
        environ.setDevice(device);
        Environ createdEnviron = environService.create(environ);
        this.template.convertAndSend("/environ/monitor",createdEnviron);
        return createdEnviron;
    }
}
