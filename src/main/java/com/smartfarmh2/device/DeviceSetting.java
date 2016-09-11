package com.smartfarmh2.device;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public class DeviceSetting {
    @Id
    @GeneratedValue
    Long id;
    @OneToOne
    Device device;
    Double waterThresholdOn;
    Double waterThresholdOff;
}
