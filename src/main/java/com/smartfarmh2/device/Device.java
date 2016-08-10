package com.smartfarmh2.device;

import com.smartfarmh2.environ.Environ;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Device {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
}
