package com.smartfarmh2.productStock;

import java.util.List;

/**
 * Created by Asus on 20/7/2559.
 */
public interface ProductStockDao {
    ProductStock create (ProductStock productStock);
    ProductStock update (ProductStock productStock);
    void delete (ProductStock productStock);
    ProductStock getProductStock (Long id);
    List<ProductStock> list();
}
