package com.smartfarmh2.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class DeviceController {
    @Autowired
    DeviceService deviceService;
    @RequestMapping(value = "/device",method = RequestMethod.GET)
    public List<Device> list(){
        return deviceService.list();
    }

    @RequestMapping(value = "/device",method = RequestMethod.POST)
    public Device create(@RequestBody Device device){
        return deviceService.create(device);
    }

    @RequestMapping(value = "/device/{id}",method = RequestMethod.GET)
    public  Device getDevice(@PathVariable("id") Long id){
        return deviceService.getDevice(id);
    }

    @RequestMapping(value = "/device/{id}",method = RequestMethod.PUT)
    public  Device edit(@PathVariable("id") Long id,@RequestBody Device device){
        return deviceService.update(device);
    }

    @RequestMapping(value = "/device/{id}",method = RequestMethod.DELETE)
    public  void delete(@PathVariable("id") Long id){
        deviceService.delete(id);
    }
}
