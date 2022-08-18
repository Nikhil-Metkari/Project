package com.Ecommerce.Category.Controller;

import static org.junit.Assert.assertEquals;
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

import com.Ecommerce.Brand.Model.Brand;
import com.Ecommerce.Brand.Repo.BrandRepo;
import com.Ecommerce.Brand.Service.BrandService;
import com.Ecommerce.Category.Controller.CategoryController;
import com.Ecommerce.Category.Model.Category;
import com.Ecommerce.Category.Repo.CategoryRepo;
import com.Ecommerce.Category.Service.CategoryService;
import com.Ecommerce.Exception.InvalidEntry;
import com.Ecommerce.Product.Model.Product;
import com.Ecommerce.Product.Repo.ProductRepo;
import com.Ecommerce.Product.Service.ProductService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CategoryControllerTest {
	
	@Mock
    private CategoryRepo categoryRepo;
	
	@InjectMocks
    private CategoryController controller;
	
	 @Mock
	 private ProductService productService;
	
	 @Mock
	  private BrandRepo brandrepo;
	  
	 @Mock
	 private BrandService brandService;

    @Mock
    private CategoryService categoryService;
    
    @Mock
    private ProductRepo productRepo;
    
    @Test
    public void getallcategoryTest() {
    	Category cat = new Category();
    	cat.setCategoryid(2);
    	cat.setCategoryname("Car");
    	cat.setCategoryenabled(true);
    	when(categoryService.getallcategory()).thenReturn(Stream.of(cat)
    			.collect(Collectors.toList()));
    	assertEquals(1,controller.getallCategory().size());
    }
    
    @Test(expected=InvalidEntry.class)
    public void getallcategoryTest_Exception() {
    	List<Category> list = new ArrayList<>();
    	when(categoryService.getallcategory()).thenReturn(list);
    	assertEquals(controller.getallCategory(), list);		
//	assertEquals(new RuntimeException("List is empty"),controller.getallCategory());
    }
    
    @Test
    public void addcategoryTest() {
    	Category cat = new Category();
    	cat.setCategoryid(2);
    	cat.setCategoryname("Car");
    	cat.setCategoryenabled(true);
    	when(categoryService.addCategory(cat)).thenReturn(cat);
    	assertEquals(cat,controller.addCategory(cat));
    }
    
    @Test(expected=InvalidEntry.class)
    public void addCategoryTest_Exception() {
    	Category cat = new Category();
    	cat.setCategoryname("abc");
    	cat.setCategoryenabled(true);
    	when(categoryService.addCategory(cat)).thenReturn(cat);
    	assertEquals(controller.addCategory(cat),cat);
    }
    
    @Test
    public void getProductByCategoryIdTest() {
    	Category cat = new Category();
    	cat.setCategoryid(6);
    	cat.setCategoryname("Footwear");
    	cat.setCategoryenabled(true);
    	 Product p = new Product();
    	 p.setProductid(1);
    	 p.setName("abc");
    	 p.setImage("xyz");
    	 p.setDetails("opq");
    	 p.setPrice(499);
    	 p.setCategoryid(999);
    	 p.setBrandid(998);
    		Brand b = new Brand();
        	b.setBrandid(1);
        	b.setBrandname("Puma");
        	b.setBrandlogo("abc");
        	b.setCategoryid(4);
    	int Categoryid = 6;
    	when(categoryRepo.existsById(cat.getCategoryid())).thenReturn(true);
    	when(productService.getProductByCategoryId(Categoryid)).thenReturn(Stream.of(p)
    			.collect(Collectors.toList()));
    	when(brandService.getProductByCategoryId(Categoryid)).thenReturn(Stream.of(b)
    			.collect(Collectors.toList()));
    	assertEquals(2,controller.getProductByCategoryId(Categoryid).size());
    }
    
    @Test(expected=InvalidEntry.class)
    public void getProductByCategoryIdTest_Exception() {
//    	Category cat = new Category();
//    	cat.setCategoryname("Footwear");
//    	cat.setCategoryenabled(true);
     	int Categoryid = 1;
    	assertEquals(1,controller.getProductByCategoryId(Categoryid).size());
    }

    @Test
    public void deleteCategoryById() {
    	Category c = new Category();
    	List<Object> li = new ArrayList<>();
		List <Product> list = new ArrayList<>();
    	when(categoryRepo.existsById(c.getCategoryid())).thenReturn(true);
    	when(productRepo.findAllByCategoryid(c.getCategoryid())).thenReturn(li);
    	when(brandrepo.findAllByCategoryid(c.getCategoryid())).thenReturn(li);
    	when(categoryService.deleteCategoryById(c.getCategoryid())).thenReturn("Deleted");
    	assertEquals(controller.deleteCategoryById(c), "Deleted");
    }
    
    @Test(expected=InvalidEntry.class)
    public void deleteCategoryById_Exception1() {
    	Category c = new Category();
    	List<Object> li = new ArrayList<>();
		List <Product> list = new ArrayList<>();
    	when(categoryRepo.existsById(c.getCategoryid())).thenReturn(false);
//    	when(productRepo.findAllByCategoryid(c.getCategoryid())).thenReturn(li);
//    	when(brandrepo.findAllByCategoryid(c.getCategoryid())).thenReturn(li);
//    	when(categoryService.deleteCategoryById(c.getCategoryid())).thenReturn("Deleted");
    	assertEquals(controller.deleteCategoryById(c), "Deleted");
    }
    
    @Test(expected=InvalidEntry.class)
    public void deleteCategoryById_Exception2() {
    	Category c = new Category();
    	 Product p = new Product();
    	 p.setProductid(1);
    	 p.setName("abc");
    	 p.setImage("xyz");
    	 p.setDetails("opq");
    	 p.setPrice(499);
    	 p.setCategoryid(999);
    	 p.setBrandid(1);
    	List<Object> li = new ArrayList<>();
		li.add(p);
    	when(categoryRepo.existsById(c.getCategoryid())).thenReturn(true);
    	when(productRepo.findAllByCategoryid(c.getCategoryid())).thenReturn(li);
//    	when(brandrepo.findAllByCategoryid(c.getCategoryid())).thenReturn(li);
//    	when(categoryService.deleteCategoryById(c.getCategoryid())).thenReturn("Deleted");
    	assertEquals(controller.deleteCategoryById(c), "Deleted");
    }
    
    @Test(expected=InvalidEntry.class)
    public void deleteCategoryById_Exception3() {
    	Category c = new Category();
    	Brand b = new Brand();
    	b.setBrandid(2);
    	b.setBrandname("abc");
    	b.setBrandlogo("xyz");
    	b.setCategoryid(6);
    	List<Object> li = new ArrayList<>();
		li.add(b);
    	when(categoryRepo.existsById(c.getCategoryid())).thenReturn(true);
   // 	when(productRepo.findAllByCategoryid(c.getCategoryid())).thenReturn(li);
    	when(brandrepo.findAllByCategoryid(c.getCategoryid())).thenReturn(li);
//    	when(categoryService.deleteCategoryById(c.getCategoryid())).thenReturn("Deleted");
    	assertEquals(controller.deleteCategoryById(c), "Deleted");
    }
    
    @Test
    public void getProductByCategorynameTest() {
    	Optional<Category> c1 = Optional.of(new Category()); 
    	List <Object> li = new ArrayList<>();
    	Category c = new Category();
    	c.setCategoryid(1);
    	c.setCategoryname("abc");
    	when(categoryRepo.findBycategoryname(c.getCategoryname())).thenReturn(c);
    	when(categoryRepo.findById(c.getCategoryid())).thenReturn(c1);
    	when(productService.getProductByCategoryId(c.getCategoryid())).thenReturn(li);
    	when(brandService.getProductByCategoryId(c.getCategoryid())).thenReturn(li);
    	assertEquals(controller.getProductByCategoryname(c.getCategoryname()),li);
    }
    
    @Test(expected=InvalidEntry.class)
    public void getProductByCategorynameTest_Exception1() {
    	Optional<Category> c1 = Optional.of(new Category()); 
    	List <Object> li = new ArrayList<>();
    	Category c = new Category();
    	c.setCategoryid(1);
    	assertEquals(controller.getProductByCategoryname(c.getCategoryname()),li);
    }
    
    @Test(expected=InvalidEntry.class)
    public void getProductByCategorynameTest_Exception2() {
    	Optional<Category> c1 =Optional.empty(); 
    	List <Object> li = new ArrayList<>();
    	Category c = new Category();
    	c.setCategoryid(1);
    	c.setCategoryname("abc");
    	when(categoryRepo.findBycategoryname(c.getCategoryname())).thenReturn(c);
    	when(categoryRepo.findById(c.getCategoryid())).thenReturn(c1);
    	assertEquals(controller.getProductByCategoryname(c.getCategoryname()),li);
    }
    
    @Test(expected=InvalidEntry.class)
    public void getProductByCategorynameTest_Exception3 () {
    	List <Category> li = new ArrayList<>();
    	Optional<Category> c1 =Optional.of(new Category()); 
    	Category c = new Category();
    	c.setCategoryid(1);
    	c.setCategoryname("abc");
    	assertEquals(controller.getProductByCategoryname(c.getCategoryname()),null);
    }
    
    @Test
    public void getProductByBodyTest() {
    	Category c = new Category();
    	c.setCategoryid(3);
    	c.setCategoryname("abcd");
    	List<Object> li = new ArrayList<>();
    	when(categoryRepo.existsById(c.getCategoryid())).thenReturn(true);
    	when(categoryRepo.findBycategoryname(c.getCategoryname())).thenReturn(c);
    	when(productService.getProductByCategoryId(c.getCategoryid())).thenReturn(li);
    	when(brandService.getProductByCategoryId(c.getCategoryid())).thenReturn(li);
    	assertEquals(controller.getProductByBody(c), li);
    }
    
    @Test(expected=InvalidEntry.class)
    public void getProductByBodyTest_Exception1() {
    	Category c = new Category();
    	c.setCategoryid(3);
//    	c.setCategoryname("abcd");
    	List<Object> li = new ArrayList<>();
//    	when(categoryRepo.existsById(c.getCategoryid())).thenReturn(true);
//    	when(categoryRepo.findBycategoryname(c.getCategoryname())).thenReturn(c);
//    	when(productService.getProductByCategoryId(c.getCategoryid())).thenReturn(li);
//    	when(brandService.getProductByCategoryId(c.getCategoryid())).thenReturn(li);
    	assertEquals(controller.getProductByBody(c), li);
    }
    
    @Test(expected=InvalidEntry.class)
    public void getProductByBodyTest_Exception4() {
    	Category c = new Category();
//    	c.setCategoryid(3);
    	c.setCategoryname("abcd");
    	List<Object> li = new ArrayList<>();
    	assertEquals(controller.getProductByBody(c), li);
    }
    
    @Test(expected=InvalidEntry.class)
    public void getProductByBodyTest_Exception2() {
    	Category c = new Category();
    	c.setCategoryid(3);
    	c.setCategoryname("abcd");
    	List<Object> li = new ArrayList<>();
    	when(categoryRepo.existsById(c.getCategoryid())).thenReturn(false);
//    	when(categoryRepo.findBycategoryname(c.getCategoryname())).thenReturn(c);
//    	when(productService.getProductByCategoryId(c.getCategoryid())).thenReturn(li);
//    	when(brandService.getProductByCategoryId(c.getCategoryid())).thenReturn(li);
    	assertEquals(controller.getProductByBody(c), li);
    }
    
    @Test(expected=InvalidEntry.class)
    public void getProductByBodyTest_Exception3() {
    	Category c = new Category();
    	c.setCategoryid(3);
    	c.setCategoryname("abcd");
    	List<Object> li = new ArrayList<>();
    	when(categoryRepo.existsById(c.getCategoryid())).thenReturn(true);
//    	when(categoryRepo.findBycategoryname(c.getCategoryname())).thenReturn(c);
//    	when(productService.getProductByCategoryId(c.getCategoryid())).thenReturn(li);
//    	when(brandService.getProductByCategoryId(c.getCategoryid())).thenReturn(li);
    	assertEquals(controller.getProductByBody(c), null);
    }
    
    
}
