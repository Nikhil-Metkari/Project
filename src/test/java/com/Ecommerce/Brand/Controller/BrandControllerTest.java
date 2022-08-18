package com.Ecommerce.Brand.Controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.Ecommerce.Brand.Controller.BrandController;
import com.Ecommerce.Brand.Model.Brand;
import com.Ecommerce.Brand.Repo.BrandRepo;
import com.Ecommerce.Brand.Service.BrandService;
import com.Ecommerce.Category.Model.Category;
import com.Ecommerce.Category.Repo.CategoryRepo;
import com.Ecommerce.Exception.InvalidEntry;
import com.Ecommerce.Product.Model.Product;
import com.Ecommerce.Product.Repo.ProductRepo;
import com.Ecommerce.Product.Service.ProductService;

@RunWith(value = MockitoJUnitRunner.class)
@SpringBootTest
public class BrandControllerTest {

	@InjectMocks
	private BrandController controller;
	
	@Mock
	private BrandService brandService;
	
	@Mock
	private BrandRepo brandRepo;
	
	@Mock
	private CategoryRepo categoryRepo;
	
	@Mock
	private ProductRepo productRepo;
	
	@Mock
	private ProductService productservice;
	
	@Test
	public void getBrandTest() {
		Brand b = new Brand();
		b.setBrandid(1);
    	b.setBrandname("Puma");
    	b.setBrandlogo("abc");
    	b.setCategoryid(4);
    	List <Brand> list = new ArrayList<>();
    	list.add(b);
//    	when(brandService.getBrand()).thenReturn(Stream.of(b).collect(Collectors.toList()));
    	when(brandService.getBrand()).thenReturn(list);
    	assertEquals(1,controller.getBrand().size());
	}
	
	@Test(expected=InvalidEntry.class)
	public void getBrandTest_Exception() {
       List<Brand> list = new ArrayList<>();
    	when(brandService.getBrand()).thenReturn(list);
     	assertEquals(1,controller.getBrand().size());
	}
	
	@Test
	public void addBrandTest() {
		Brand brand = new Brand();
		brand.setBrandid(1);
		brand.setBrandname("Puma");
		brand.setBrandlogo("abc");
		brand.setCategoryid(3);
    	 when(categoryRepo.existsById(brand.getCategoryid())).thenReturn(true);
    //	 when(categoryRepo.findById(brand.getCategoryid()).get().isCategoryenabled()).thenReturn(true);
    	 when(brandService.addBrand(brand)).thenReturn(brand);
		   assertEquals(controller.addBrand(brand), brand);
	}
	
	@Test(expected=InvalidEntry.class)
	public void addBrandTest_Exception() {
		Brand brand = new Brand();
    	 when(categoryRepo.existsById(brand.getCategoryid())).thenReturn(false);
		   assertEquals(controller.addBrand(brand), brand);
	}
	
	@Test
	public void updateBrandTest() {
		Brand brand = new Brand();
		brand.setBrandid(1);
		brand.setBrandname("Puma");
		brand.setBrandlogo("abc");
		brand.setCategoryid(3);
    	 when(brandRepo.existsById(brand.getBrandid())).thenReturn(true);
    	 when(categoryRepo.existsById(brand.getCategoryid())).thenReturn(true);
    	 when(brandService.updateBrand(brand)).thenReturn(brand);
		   assertEquals(controller.updateBrand(brand), brand);
	}

	@Test(expected=InvalidEntry.class)
	public void updateBrandTest_Exception1() {
		Brand brand = new Brand();
    	 when(brandRepo.existsById(brand.getBrandid())).thenReturn(false);
		   assertEquals(controller.updateBrand(brand), brand);
	}
	@Test(expected=InvalidEntry.class)
	public void updateBrandTest_Exception2() {
		Brand brand = new Brand();
    	 when(brandRepo.existsById(brand.getBrandid())).thenReturn(true);
    	 when(categoryRepo.existsById(brand.getCategoryid())).thenReturn(false);
		   assertEquals(controller.updateBrand(brand), brand);
	}
	
	@Test
	public void getProductByBrandnameTest() {
		Optional<Brand> br = Optional.of(new Brand());
		Brand b = new Brand();
		b.setBrandid(2);
		b.setBrandname("abc");
		List<Product> list = new ArrayList<>();
		when(brandRepo.findBybrandname(b.getBrandname())).thenReturn(b);
		when(brandRepo.findById(b.getBrandid())).thenReturn(br);
		when(productservice.getProductByBrandId(b.getBrandid())).thenReturn(list);
		assertEquals(controller.getProductByBrandname(b.getBrandname()),list);
	}
	
	@Test(expected=InvalidEntry.class)
	public void getProductByBrandnameTest_Exception1() {
		Optional<Brand> br = Optional.of(new Brand());
		Brand b = new Brand();
		b.setBrandid(2);
		b.setBrandname("abc");
		List<Product> list = new ArrayList<>();
		when(brandRepo.findBybrandname(b.getBrandname())).thenReturn(b);
//		when(brandRepo.findById(b.getBrandid())).thenReturn(br);
//		when(productservice.getProductByBrandId(b.getBrandid())).thenReturn(list);
		assertEquals(controller.getProductByBrandname(b.getBrandname()),list);
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
    	 p.setBrandid(2);
    		Brand brand = new Brand();
    		brand.setBrandid(2);
    		brand.setBrandname("Puma");
    		brand.setBrandlogo("abc");
    		brand.setCategoryid(3);
    	int brandid =2;
    	List <Product> list = new ArrayList<>();
    	list.add(p);
    	Optional<Product> optional = list.stream().findFirst();
       	List <Brand> list1 = new ArrayList<>();
    	list1.add(brand);
    	Optional<Brand> optional1 = list1.stream().findFirst();
    	when(brandRepo.findById(brandid)).thenReturn(optional1);
    	when(productservice.getProductByBrandId(brandid)).thenReturn(list);
        assertEquals(1,controller.getProductByBrandId(brandid).size());
 //   	assertEquals(optional,controller.getProductByBrandId(brandid));
	}
	
	@Test(expected=InvalidEntry.class)
	public void getProductByBrandIdTest_Exception() {
		Brand brand = new Brand();
	int brandid =2;
	Optional<Brand> optional1 = Optional.empty();
//	when(brandRepo.findById(brandid)).thenReturn(optional1);
	assertEquals(optional1,controller.getProductByBrandId(brandid).size());
}
	
	@Test
	public void deleteBrandByIdTest() {
		Brand b = new Brand();
		List <Product> list = new ArrayList<>();
		int id =3;
		when(brandRepo.existsById(b.getBrandid())).thenReturn(true);
		when(productRepo.findAllByBrandid(b.getBrandid())).thenReturn(list);
		//when(productRepo.findAllByBrandid(b.getBrandid()).isEmpty()).thenReturn(false);
	//	when(brandService.deleteBrandById(id)).thenReturn("Deleted");
		assertEquals(controller.deleteBrandById(b),"Deleted");
	}
	
	@Test(expected=InvalidEntry.class)
	public void deleteBrandByIdTest_Exception1() {
		Brand b = new Brand();
		int id =3;
		when(brandRepo.existsById(b.getBrandid())).thenReturn(false);
		assertEquals(controller.deleteBrandById(b),"Deleted");
	}
	
	@Test(expected=InvalidEntry.class)
	public void deleteBrandByIdTest_Exception2() {
		Brand b = new Brand();
		int id =3;
		 Product p = new Product();
    	 p.setProductid(1);
    	 p.setName("abc");
    	 p.setImage("xyz");
    	 p.setDetails("opq");
    	 p.setPrice(499);
    	 p.setCategoryid(999);
    	 p.setBrandid(2);
		List <Product> list = new ArrayList<>();
		list.add(p);
		when(brandRepo.existsById(b.getBrandid())).thenReturn(true);
		when(productRepo.findAllByBrandid(b.getBrandid())).thenReturn(list);
		assertEquals(controller.deleteBrandById(b),"Deleted");
	}

	@Test
	public void getProductByBodyTest() {
		 Product p = new Product();
    	 p.setProductid(1);
    	 p.setName("abc");
    	 p.setImage("xyz");
    	 p.setDetails("opq");
    	 p.setPrice(499);
    	 p.setCategoryid(999);
    	 p.setBrandid(1);
    	 Brand b = new Brand();
    	 b.setBrandid(1);
		List <Product> list = new ArrayList<>();
		list.add(p);
		when(brandRepo.existsById(b.getBrandid())).thenReturn(true);
		when(productservice.getProductByBrandId(p.getBrandid())).thenReturn(list);
		assertEquals(controller.getProductByBody(b),list);
	}
	
	@Test(expected=InvalidEntry.class)
	public void getProductByBodyTest_Exception1() {
		 Product p = new Product();
    	 Brand b = new Brand();
    	 b.setBrandid(1);
		List <Product> list = new ArrayList<>();
		when(brandRepo.existsById(b.getBrandid())).thenReturn(false);
		assertEquals(controller.getProductByBody(b),list);
	}
	
	@Test(expected=InvalidEntry.class)
	public void getProductByBodyTest_Exception2() {
		 Product p = new Product();
    	 Brand b = new Brand();
    	 b.setBrandid(0);
		List <Product> list = new ArrayList<>();
//		when(brandRepo.existsById(b.getBrandid())).thenReturn(true);
//	  when(productservice.getProductByBrandId(p.getBrandid())).thenReturn(list);
		assertEquals(controller.getProductByBody(b),list);
	}
	
	@Test
	public void getProductlistByBodyNameTest() {
		 Product p = new Product();
    	 p.setProductid(1);
    	 p.setName("abc");
    	 p.setImage("xyz");
    	 p.setDetails("opq");
    	 p.setPrice(499);
    	 p.setCategoryid(999);
    	 p.setBrandid(1);
    	 Brand b = new Brand();
    	 b.setBrandid(1);
    	 b.setBrandname("abc");
    	 List<Brand> list1 = new ArrayList<>();
    	 list1.add(b);
    	// String str = b.getBrandname();
    //	int id = brandRepo.findBybrandname(b.getBrandname()).getBrandid();
		List <Product> list = new ArrayList<>();
		list.add(p);
		when(brandRepo.findBybrandname(b.getBrandname())).thenReturn(b);
		when(productservice.getProductByBrandId(b.getBrandid())).thenReturn(list);
		assertEquals(controller.getProductByBodyName(b),list);
	}
	
	@Test(expected=InvalidEntry.class)
	public void getProductlistByBodyNameTest_Exception1() {
		 Product p = new Product();
    	 Brand b = new Brand();
    	 b.setBrandid(1);
    	 List<Brand> list1 = new ArrayList<>();
    	 list1.add(b);
		List <Product> list = new ArrayList<>();
		list.add(p);
	//	when(brandRepo.findBybrandname(b.getBrandname())).thenReturn(b);
//		when(productservice.getProductByBrandId(b.getBrandid())).thenReturn(list);
		assertEquals(controller.getProductByBodyName(b),list);
	}
	
	@Test(expected=InvalidEntry.class)
	public void getProductlistByBodyNameTest_Exception2() {
		 Product p = new Product();
    	 Brand b = new Brand();
    	 b.setBrandid(1);
    	 b.setBrandname("abc");
    	String str = b.getBrandname();
    //	int id = brandRepo.findBybrandname(b.getBrandname()).getBrandid();
		List <Product> list = new ArrayList<>();
//    	when(brandRepo.findBybrandname(b.getBrandname())).thenReturn(null);
//    	when(productservice.getProductByBrandId(b.getBrandid())).thenReturn(list);
		assertEquals(controller.getProductByBodyName(b),null);
	}
	
}
