package com.smartfarmh2.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class DeviceSettingController {
    @Autowired
    DeviceSettingService deviceSettingService;
    @RequestMapping(value = "/deviceSetting",method = RequestMethod.GET)
    public List<DeviceSetting> list(){
        return deviceSettingService.list();
    }

    @RequestMapping(value = "/deviceSetting",method = RequestMethod.POST)
    public DeviceSetting create(@RequestBody DeviceSetting deviceSetting){
        return deviceSettingService.create(deviceSetting);
    }

    @RequestMapping(value = "/deviceSetting/{id}",method = RequestMethod.GET)
    public  DeviceSetting getDevice(@PathVariable("id") Long id){
        return deviceSettingService.getDeviceSetting(id);
    }

    @RequestMapping(value = "/deviceSetting/{id}",method = RequestMethod.PUT)
    public  DeviceSetting edit(@PathVariable("id") Long id,@RequestBody DeviceSetting deviceSetting){
        return deviceSettingService.update(deviceSetting);
    }

    @RequestMapping(value = "/deviceSetting/{id}",method = RequestMethod.DELETE)
    public  void delete(@PathVariable("id") Long id){
        deviceSettingService.delete(id);
    }
}
