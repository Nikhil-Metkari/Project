package com.Ecommerce.Product.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import com.Ecommerce.Category.Model.Category;
import com.Ecommerce.Product.Model.Product;
import com.Ecommerce.Product.Repo.ProductRepo;
import com.Ecommerce.Product.Service.ProductService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Mock
    private ProductRepo productrepo;
    
    @InjectMocks
    private ProductService productservice;
  
    @Test
     public void getProductTest() {
    	 Product p = new Product();
    	 p.setProductid(1);
    	 p.setName("abc");
    	 p.setImage("xyz");
    	 p.setDetails("opq");
    	 p.setPrice(499);
    	 p.setCategoryid(999);
    	 p.setBrandid(998);
    	 when(productrepo.findAll()).thenReturn(Stream.of(p)
     			.collect(Collectors.toList()));
    	 assertEquals(1,productservice.getProduct().size());
     }

    @Test
    public void updateProductTest() {
   	 Product p = new Product();
   	 p.setProductid(1);
   	 p.setName("abc");
   	 p.setImage("xyz");
   	 p.setDetails("opq");
   	 p.setPrice(499);
   	 p.setCategoryid(999);
   	 p.setBrandid(998);
   	 when(productrepo.save(p)).thenReturn(p);
   	 assertEquals(p,productservice.addProduct(p));
   	assertEquals(p,productservice.updateProduct(p));
    }
    
    @Test
    public void getProductByBrandIdTest() {
   	 Product p = new Product();
   	 p.setProductid(1);
   	 p.setName("abc");
   	 p.setImage("xyz");
   	 p.setDetails("opq");
   	 p.setPrice(499);
   	 p.setCategoryid(999);
   	 p.setBrandid(998);
   	 int id = 454;
   	 when(productrepo.findAllByBrandid(id)).thenReturn(Stream.of(p)
    			.collect(Collectors.toList()));
   	when(productrepo.findAllByCategoryid(id)).thenReturn(Stream.of(p)
			.collect(Collectors.toList()));
   	 assertEquals(1,productservice.getProductByBrandId(id).size());
   	assertEquals(1,productservice.getProductByCategoryId(id).size());
    }

    @Test
    public void deleteproductTest() {
     	 Product p = new Product();
       	 p.setProductid(1);
       	 p.setName("abc");
       	 p.setImage("xyz");
       	 p.setDetails("opq");
       	 p.setPrice(499);
       	 p.setCategoryid(999);
       	 p.setBrandid(998);
       	List<Product> list = new ArrayList<>();
		list.add(p);
		Optional<Product> optional = list.stream().findFirst();
		
//		given(productrepo.findById(p.getProductid())).willReturn(optional);
//	    when(productrepo.deleteById(p.getProductid())).thenReturn(optional);
	    
//		assertEquals(productservice.deleteProduct(p.getProductid()));
		assertEquals(productservice.deleteProduct(p.getProductid()),"deleted");
}

    
    
    
    
}
