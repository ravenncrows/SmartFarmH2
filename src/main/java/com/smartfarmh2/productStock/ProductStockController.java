package com.smartfarmh2.productStock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Asus on 20/7/2559.
 */
@CrossOrigin
@RestController
public class ProductStockController {
    @Autowired
    ProductStockService productStockService;

    @RequestMapping(value = "/productStock",method = RequestMethod.GET)
    public List<ProductStock> list(){
        return productStockService.list();
    }

    @RequestMapping(value = "/productStock",method = RequestMethod.POST)
    public ProductStock create(@RequestBody ProductStock productStock){
        return productStockService.create(productStock);
    }

    @RequestMapping(value = "/productStock/{id}",method = RequestMethod.GET)
    public  ProductStock getProduct(@PathVariable("id") Long id){
        return productStockService.getProductStock(id);
    }

    @RequestMapping(value = "/productStock/{id}",method = RequestMethod.PUT)
    public  ProductStock edit(@PathVariable("id") Long id,@RequestBody ProductStock productStock){
        return productStockService.update(productStock);
    }

    @RequestMapping(value = "/productStock/{id}",method = RequestMethod.DELETE)
    public  void delete(@PathVariable("id") Long id){
        productStockService.delete(id);
    }
}
