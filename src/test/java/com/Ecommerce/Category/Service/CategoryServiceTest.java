package com.Ecommerce.Category.Service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import org.springframework.boot.test.context.SpringBootTest;

import com.Ecommerce.Category.Model.Category;
import com.Ecommerce.Category.Repo.CategoryRepo;
import com.Ecommerce.Category.Service.CategoryService;
import com.Ecommerce.Product.Model.Product;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CategoryServiceTest {

	@InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepo categoryRepo;

    @Test
    public void getallcategoryTest() {
    	Category cat = new Category();
    	cat.setCategoryid(1);
    	cat.setCategoryname("Footwear");
    	cat.setCategoryenabled(true);
    	when(categoryRepo.findAll()).thenReturn(Stream.of(cat)
    			.collect(Collectors.toList()));
    	assertEquals(1,categoryService.getallcategory().size());
    }
    
    @Test
    public void addCategoryTest() {
    	Category cat = new Category();
    	cat.setCategoryid(1);
    	cat.setCategoryname("Footwear");
    	cat.setCategoryenabled(true);
    	when(categoryRepo.save(cat)).thenReturn(cat);
    	assertEquals(cat,categoryService.addCategory(cat));
    	
    }
    
    @Test
    public void deleteCategoryByIdTest() {
    	Category cat = new Category();
    	cat.setCategoryid(1);
    	cat.setCategoryname("Footwear");
    	cat.setCategoryenabled(true);;
       	List<Category> list = new ArrayList<>();
		list.add(cat);
		Optional<Category> optional = list.stream().findFirst();
   assertEquals(categoryService.deleteCategoryById(cat.getCategoryid()),"deleted");
    }
    

   
}

