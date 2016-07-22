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

    @PrePersist
    void preInsert() {
        this.createdAt = LocalDateTime.now();
    }
}
