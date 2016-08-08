package com.smartfarmh2.device;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


public class DeviceSetting {
    @Id
    @GeneratedValue
    Long id;
    @OneToOne
    Device device;
    Double waterThreshold;
}
