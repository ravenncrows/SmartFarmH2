package com.smartfarmh2.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeviceSettingDaoImpl implements DeviceSettingDao {
    @Autowired
    DeviceSettingRepository deviceSettingRepository;
    @Override
    public DeviceSetting create(DeviceSetting deviceSetting) {
        return deviceSettingRepository.save(deviceSetting);
    }

    @Override
    public DeviceSetting update(DeviceSetting deviceSetting) {
        return deviceSettingRepository.save(deviceSetting);
    }

    @Override
    public void delete(Long id) {
        deviceSettingRepository.delete(id);
    }

    @Override
    public DeviceSetting getDeviceSetting(Long id) {
        return deviceSettingRepository.findOne(id);
    }

    @Override
    public List<DeviceSetting> list() {
        return deviceSettingRepository.findAll();
    }
}
