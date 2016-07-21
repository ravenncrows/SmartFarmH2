package com.smartfarmh2.product;

import com.smartfarmh2.productStock.ProductStock;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Asus on 20/7/2559.
 */
@Entity
@Data
public class Product implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String unit;
    
    public Product(){}
    public Product(String name,String unit) {
        this.name = name;
        this.unit = unit;
    }

}
