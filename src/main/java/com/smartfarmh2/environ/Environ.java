package com.smartfarmh2.environ;
import com.smartfarmh2.device.Device;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
public class Environ implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
    private Double temp;
    private Double humid;
    private Double soil;
    private LocalDateTime createdDate;
    @ManyToOne
    private Device device;

    public Environ() {
    }

    public Environ(Double temp, Double humid, Double soil) {
        this.temp = temp;
        this.humid = humid;
        this.soil = soil;
        this.createdDate = LocalDateTime.now();
    }

    @PrePersist
    void preInsert() {
        this.createdDate = LocalDateTime.now();
    }
}
