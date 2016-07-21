package com.smartfarmh2.productStock;

import com.smartfarmh2.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Asus on 20/7/2559.
 */
public interface ProductStockRepository extends JpaRepository<ProductStock,Long> {
    List<ProductStock> findByProduct(Product product);
}
