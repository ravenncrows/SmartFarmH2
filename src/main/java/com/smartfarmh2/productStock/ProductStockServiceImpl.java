package com.smartfarmh2.productStock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Asus on 20/7/2559.
 */
@Service
public class ProductStockServiceImpl implements ProductStockService{
    @Autowired
    ProductStockDao productStockDao;
    @Override
    public ProductStock create(ProductStock productStock) {
        LocalDate create = null;
        productStock.setCreatedDate(create.now());
        return productStockDao.create(productStock);
    }

    @Override
    public ProductStock update(ProductStock productStock) {
        return productStockDao.update(productStock);
    }

    @Override
    public void delete(Long id) {
        ProductStock ps = productStockDao.getProductStock(id);
        productStockDao.delete(ps);
    }

    @Override
    public ProductStock getProductStock(Long id) {
        return productStockDao.getProductStock(id);
    }

    @Override
    public List<ProductStock> list() {
        return productStockDao.list();
    }
}
