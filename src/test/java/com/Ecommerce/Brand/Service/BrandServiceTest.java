package com.Ecommerce.Brand.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.Ecommerce.Brand.Model.Brand;
import com.Ecommerce.Brand.Repo.BrandRepo;
import com.Ecommerce.Brand.Service.BrandService;
import com.Ecommerce.Category.Repo.CategoryRepo;
import com.Ecommerce.Product.Repo.ProductRepo;
import com.Ecommerce.Product.Service.ProductService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BrandServiceTest {

	
    @Mock
   private BrandRepo brandrepo;
    
    @InjectMocks
    private BrandService brandService;
   
    
    @Test
    public void getBrandTest() {
    	Brand b = new Brand();
    	b.setBrandid(1);
    	b.setBrandname("Puma");
    	b.setBrandlogo("abc");
    	b.setCategoryid(4);
    	when(brandrepo.findAll()).thenReturn(Stream.of(b)
    			.collect(Collectors.toList()));
    	assertEquals(1,brandService.getBrand().size());
    }
    
    @Test
    public void addBrandTest() {
    	Brand b = new Brand();
    	b.setBrandid(1);
    	b.setBrandname("Puma");
    	b.setBrandlogo("abc");
    	b.setCategoryid(4);
    	when(brandrepo.save(b)).thenReturn(b);
    	assertEquals(b,brandService.addBrand(b));
    	assertEquals(b,brandService.updateBrand(b));
    }
    
    @Test
    public void getProductbyCategoryIdTest() {
    	Brand b = new Brand();
    	b.setBrandid(1);
    	b.setBrandname("Puma");
    	b.setBrandlogo("abc");
    	b.setCategoryid(4);
    	int id = 5;
    	when(brandrepo.findAllByCategoryid(id)).thenReturn(Stream.of(b)
    			.collect(Collectors.toList()));
    	assertEquals(1,brandService.getProductByCategoryId(id).size());
    }
    
    @Test
    public void deleteBrandById() {
    	Brand b = new Brand();
    	List <Brand> list = new ArrayList<>();
    	list.add(b);
    	Optional<Brand> op = list.stream().findFirst();
    	assertEquals(brandService.deleteBrandById(b.getBrandid()), "deleted");
    }
}
