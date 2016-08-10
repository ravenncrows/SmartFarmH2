package com.smartfarmh2.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeviceDaoImpl implements DeviceDao {
    @Autowired
    DeviceRepository deviceRepository;

    @Override
    public Device create(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public Device update(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public void delete(Long id) {
        deviceRepository.delete(id);
    }

    @Override
    public Device getDevice(Long id) {
        return deviceRepository.findOne(id);
    }

    @Override
    public List<Device> list() {
        return deviceRepository.findAll();
    }

    @Override
    public Device findOneByName(String name) {
        return deviceRepository.findOneByName(name);
    }
}
