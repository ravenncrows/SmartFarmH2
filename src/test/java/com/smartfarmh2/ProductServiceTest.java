package com.smartfarmh2;


import com.smartfarmh2.product.Product;
import com.smartfarmh2.product.ProductDao;
import com.smartfarmh2.product.ProductService;
import com.smartfarmh2.product.ProductServiceImpl;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import static org.mockito.Mockito.*;
/**
 * Created by Asus on 22/7/2559.
 */
public class ProductServiceTest {
    @Test
    public void createProduct (){
        Product p = mock(Product.class);
        when(p.getName()).thenReturn("Mango");
        when(p.getUnit()).thenReturn("kilogram");
        ProductDao pDao = mock(ProductDao.class);
        when(pDao.create(p)).thenReturn(p);

        ProductService productService = new ProductServiceImpl(pDao);
        assertThat(productService.create(p).getName(),is("Mango"));
    }


}
