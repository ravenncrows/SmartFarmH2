package com.smartfarmh2.product;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Asus on 20/7/2559.
 */
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByName(String name);
}