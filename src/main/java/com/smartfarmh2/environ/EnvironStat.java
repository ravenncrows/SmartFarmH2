package com.smartfarmh2.environ;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Entity
@Data
public class EnvironStat {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime createdAt;
    private Double averageHumid;
    private Double lowestHumid;
    private Double highestHumid;
    private Double averageTemp;
    private Double lowestTemp;
    private Double highestTemp;
    private Double averageSoil;
    private Double lowestSoil;
    private Double highestSoil;

    public EnvironStat(){
        this.averageHumid = 0.0;
        this.lowestHumid = 0.0;
        this.highestHumid = 0.0;
        this.averageTemp = 0.0;
        this.lowestTemp = 0.0;
        this.highestTemp = 0.0;
        this.averageSoil = 0.0;
        this.lowestSoil = 0.0;
        this.highestSoil = 0.0;
    }
    @PrePersist
    void preInsert() {
        this.createdAt = LocalDateTime.now();
    }
}
