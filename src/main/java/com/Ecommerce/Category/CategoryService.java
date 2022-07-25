package com.Ecommerce.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommerce.Brand.Brand;
import com.Ecommerce.Brand.BrandRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {


    @Autowired
    private CategoryRepo categoryRepo;
    
    @Autowired
    private BrandRepo brandrepo;

    public List<Category> getallcategory() {
        return categoryRepo.findAll();
    }

    public Category addCategory(Category category) {
        return categoryRepo.save(category);
    }

	public void deleteCategoryById(int categoryid) {
		categoryRepo.deleteById(categoryid);
		
	}
    
    
}



