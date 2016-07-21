package com.smartfarmh2.product;

import java.util.List;

/**
 * Created by Asus on 20/7/2559.
 */
public interface ProductDao {
    Product create (Product product);
    Product update(Product product);
    void delete(Product product);
    Product getProduct(Long id);
    List<Product> list();
}
