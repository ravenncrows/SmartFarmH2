package com.smartfarmh2.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Asus on 20/7/2559.
 */
@CrossOrigin
@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @RequestMapping(value = "/product",method = RequestMethod.GET)
    public List<Product> list(){
        return productService.list();
    }

    @RequestMapping(value = "/product",method = RequestMethod.POST)
    public Product create(@RequestBody Product product){
        return productService.create(product);
    }

    @RequestMapping(value = "/product/{id}",method = RequestMethod.GET)
    public  Product getProduct(@PathVariable("id") Long id){
        return productService.getProduct(id);
    }

    @RequestMapping(value = "/product/{id}",method = RequestMethod.PUT)
    public  Product edit(@PathVariable("id") Long id,@RequestBody Product product){
        return productService.update(product);
    }

    @RequestMapping(value = "/product/{id}",method = RequestMethod.DELETE)
    public  void delete(@PathVariable("id") Long id){
        productService.delete(id);
    }
}
