package com.smartfarmh2.device;

import com.smartfarmh2.environ.Environ;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Device {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
