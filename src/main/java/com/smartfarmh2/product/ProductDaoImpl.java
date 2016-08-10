package com.smartfarmh2.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Asus on 20/7/2559.
 */
@Repository
public class ProductDaoImpl implements ProductDao {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public List<Product> list() {
        return productRepository.findAll();
    }
}
