package com.smartfarmh2.productStock;

import java.util.List;

/**
 * Created by Asus on 20/7/2559.
 */
public interface ProductStockService {
    ProductStock create (ProductStock productStock);
    ProductStock update (ProductStock productStock);
    void delete (Long id);
    ProductStock getProductStock (Long id);
    List<ProductStock> list();
}
