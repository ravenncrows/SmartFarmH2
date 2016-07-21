package com.smartfarmh2.productStock;

import com.smartfarmh2.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Asus on 20/7/2559.
 */
@Repository
public class ProductStockDaoImpl implements ProductStockDao{
    @Autowired
    ProductStockRepository productStockRepository;
    @Autowired
    ProductRepository productRepository;
    @Override
    public ProductStock create(ProductStock productStock) {
        productStock.setProduct(productRepository.findByName(productStock.getProduct().getName()));
        return productStockRepository.save(productStock);
    }

    @Override
    public ProductStock update(ProductStock productStock) {
        return productStockRepository.save(productStock);
    }

    @Override
    public void delete(ProductStock productStock) {
        productStockRepository.delete(productStock);
    }

    @Override
    public ProductStock getProductStock(Long id) {
        return productStockRepository.findOne(id);
    }

    @Override
    public List<ProductStock> list() {
        return productStockRepository.findAll();
    }
}

