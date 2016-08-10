package com.smartfarmh2.device;

import java.util.List;

public interface DeviceService {
    Device create (Device device);
    Device update(Device device);
    void delete(Long id);
    Device getDevice(Long id);
    List<Device> list();
    Device findOneByName(String name);
    Boolean turnWaterOn(String deviceName);
}
