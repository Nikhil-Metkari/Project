package com.Ecommerce.Product.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Ecommerce.Brand.Repo.BrandRepo;
import com.Ecommerce.Category.Model.Category;
import com.Ecommerce.Category.Repo.CategoryRepo;
import com.Ecommerce.Exception.InvalidEntry;
import com.Ecommerce.Product.Controller.ProductController;
import com.Ecommerce.Product.Model.Product;
import com.Ecommerce.Product.Repo.ProductRepo;
import com.Ecommerce.Product.Service.ProductService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ProductControllerTest {

	
	@Mock
    private ProductService productService;
	
	@InjectMocks
    private ProductController pcontroller;
	
	@Mock
    private ProductRepo productRepo;

	  @Mock
	    private BrandRepo brandrepo;

	    @Mock
	    private CategoryRepo categoryRepo;
	    
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
    	 when(productService.getProduct()).thenReturn(Stream.of(p)
     			.collect(Collectors.toList()));
    	 assertEquals(1,pcontroller.getProduct().size());
	}
	
	   @Test(expected=InvalidEntry.class)
	    public void getProductTest_Exception() {
		   Product p = new Product();
	    	List<Product> list = new ArrayList<>();
	    	when( productService.getProduct()).thenReturn(list);
	    	assertEquals(pcontroller.getProduct(), list);		 
	   }
	   
	   @Test
	   public void addProductTest() {
			 Product p = new Product();
	    	 p.setProductid(1);
	    	 p.setName("abc");
	    	 p.setImage("xyz");
	    	 p.setDetails("opq");
	    	 p.setPrice(499);
	    	 p.setCategoryid(999);
	    	 p.setBrandid(998);
	    	 int id = 1;
	    	 when(categoryRepo.existsById(p.getCategoryid())).thenReturn(true);
	    	 when(brandrepo.existsById(p.getBrandid())).thenReturn(true);
	    	 when(productRepo.existsById(p.getProductid())).thenReturn(false);
		   when(productService.addProduct(p)).thenReturn(p);
		   assertEquals(pcontroller.addProduct(p), p);
	   }
	 
	   @Test(expected=InvalidEntry.class)
	   public void addProductTest_Exception() {
			 Product p = new Product();
	    	 when(categoryRepo.existsById(p.getCategoryid())).thenReturn(false);
//	    	 when(brandrepo.existsById(p.getBrandid())).thenReturn(true);
//	    	 when(productRepo.existsById(p.getProductid())).thenReturn(true);
//		   when(productService.addProduct(p)).thenReturn(p);
		   assertEquals(pcontroller.addProduct(p), p);
	   }
	   
	   @Test(expected=InvalidEntry.class)
	   public void addProductTest_Exception1() {
			 Product p = new Product();
	     when(categoryRepo.existsById(p.getCategoryid())).thenReturn(true);
   	 when(brandrepo.existsById(p.getBrandid())).thenReturn(false);
		   assertEquals(pcontroller.addProduct(p), p);
	   }
	   
	   @Test(expected=InvalidEntry.class)
	   public void addProductTest_Exception2() {
			 Product p = new Product();
	    	when(categoryRepo.existsById(p.getCategoryid())).thenReturn(true);
	    	 when(brandrepo.existsById(p.getBrandid())).thenReturn(true);
	    	 when(productRepo.existsById(p.getProductid())).thenReturn(true);
//		   when(productService.addProduct(p)).thenReturn(p);
		   assertEquals(pcontroller.addProduct(p), p);
	   }
	   
	   @Test
	   public void updateProductTest() {
			 Product p = new Product();
		 	// Optional<Product> po = Optional.empty();
	    	 p.setProductid(1);
	    	 p.setName("abc");
	    	 p.setImage("xyz");
	    	 p.setDetails("opq");
	    	 p.setPrice(499);
	    	 p.setCategoryid(999);
	    	 p.setBrandid(998);
	    	 when(productRepo.existsById(p.getProductid())).thenReturn(true);
	    	 when(categoryRepo.existsById(p.getCategoryid())).thenReturn(true);
	    	 when(brandrepo.existsById(p.getBrandid())).thenReturn(true);
	    	 when(productService.updateProduct(p)).thenReturn(p);
	    	 assertEquals(pcontroller.updateProduct(p), p);
	   }
	   
	   @Test(expected=InvalidEntry.class)
	   public void updateProductTest_Exception1() {
			 Product p = new Product();
			 p.setProductid(1);
	    	 p.setName("abc");
	    	 p.setImage("xyz");
	    	 p.setDetails("opq");
	    	 p.setPrice(499);
	    	 p.setCategoryid(999);
	    	 p.setBrandid(998);
         	 when(productRepo.existsById(p.getProductid())).thenReturn(false);
	    	 assertEquals(pcontroller.updateProduct(p), p);
	   }
	   
	   @Test(expected=InvalidEntry.class)
	   public void updateProductTest_Exception2() {
			 Product p = new Product();
			 p.setProductid(1);
	    	 p.setName("abc");
	    	 p.setImage("xyz");
	    	 p.setDetails("opq");
	    	 p.setPrice(499);
	    	 p.setCategoryid(999);
	    	 p.setBrandid(998);
	    	 when(productRepo.existsById(p.getProductid())).thenReturn(true);
	    	 when(categoryRepo.existsById(p.getCategoryid())).thenReturn(false);
//	    	 when(brandrepo.existsById(p.getBrandid())).thenReturn(true);
//	    	 when(productService.updateProduct(p)).thenReturn(p);
	    	 assertEquals(pcontroller.updateProduct(p), p);
	   }
	   
	   @Test(expected=InvalidEntry.class)
	   public void updateProductTest_Exception3() {
			 Product p = new Product();
	    	 when(productRepo.existsById(p.getProductid())).thenReturn(true);
	    	 when(categoryRepo.existsById(p.getCategoryid())).thenReturn(true);
	    	 when(brandrepo.existsById(p.getBrandid())).thenReturn(false);
//	    	 when(productService.updateProduct(p)).thenReturn(p);
	    	 assertEquals(pcontroller.updateProduct(p), p);
	   }
	   
	   @Test
	   public void deleteProductTest() {
			 Product p = new Product();
	    	 when(productRepo.existsById(p.getProductid())).thenReturn(true);
	    	 when(productService.deleteProduct(p.getProductid())).thenReturn("deleted");
	    	 assertEquals(pcontroller.deleteProduct(p),"deleted");
	   }

	   @Test(expected=InvalidEntry.class)
	   public void deleteProductTest_Exception() {
			 Product p = new Product();
	    	 when(productRepo.existsById(p.getProductid())).thenReturn(false);
	    	// when(productService.deleteProduct(p.getProductid())).thenReturn("deleted");
	    	 assertEquals(pcontroller.deleteProduct(p),"deleted");
	   }

}
