package com.smartfarmh2.environ;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
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
