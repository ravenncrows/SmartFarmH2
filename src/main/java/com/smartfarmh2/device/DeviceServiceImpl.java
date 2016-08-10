package com.smartfarmh2.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    DeviceDao deviceDao;

    @Override
    public Device create(Device device) {
        return deviceDao.create(device);
    }

    @Override
    public Device update(Device device) {
        return deviceDao.update(device);
    }

    @Override
    public void delete(Long id) {
        deviceDao.delete(id);
    }

    @Override
    public Device getDevice(Long id) {
        return deviceDao.getDevice(id);
    }

    @Override
    public List<Device> list() {
        return deviceDao.list();
    }

    @Override
    public Device findOneByName(String name) {
        return deviceDao.findOneByName(name);
    }

    @Override
    public Boolean turnWaterOn(String deviceName) {
        return false;
    }
}
