package com.smartfarmh2.device;

import java.util.List;


public interface DeviceSettingService {
    DeviceSetting create (DeviceSetting deviceSetting);
    DeviceSetting update(DeviceSetting deviceSetting);
    void delete(Long id);
    DeviceSetting getDeviceSetting(Long id);
    List<DeviceSetting> list();
}
