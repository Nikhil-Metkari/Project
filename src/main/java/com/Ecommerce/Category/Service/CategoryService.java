package com.Ecommerce.Category.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommerce.Brand.Model.Brand;
import com.Ecommerce.Brand.Repo.BrandRepo;
import com.Ecommerce.Category.Model.Category;
import com.Ecommerce.Category.Repo.CategoryRepo;

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

	public String deleteCategoryById(int categoryid) {
		categoryRepo.deleteById(categoryid);
		return "deleted";
	}
    
    
}



