package com.smartfarmh2.productStock;

import com.smartfarmh2.product.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Asus on 20/7/2559.
 */
@Entity
@Data
@NoArgsConstructor
public class ProductStock implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
    private int quantity;
    private LocalDate createdDate;

    public ProductStock(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.createdDate = LocalDate.now();
    }
}
