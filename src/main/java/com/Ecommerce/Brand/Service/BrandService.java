package com.Ecommerce.Brand.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommerce.Brand.Model.Brand;
import com.Ecommerce.Brand.Repo.BrandRepo;
import com.Ecommerce.Category.Model.Category;
import com.Ecommerce.Category.Repo.CategoryRepo;
import com.Ecommerce.Category.Service.CategoryService;
import com.Ecommerce.Exception.InvalidEntry;
import com.Ecommerce.Product.Model.Product;
import com.Ecommerce.Product.Repo.ProductRepo;
import com.Ecommerce.Product.Service.ProductService;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
	Logger log = LoggerFactory.getLogger(CategoryService.class);
	
    @Autowired
   private BrandRepo brandrepo;
    
    @Autowired
   private CategoryRepo catrgoryRepo;
    
    @Autowired
    private ProductRepo productRepo;
    
    @Autowired
    private ProductService productservice;
    
    public List<Brand> getBrand() {
        return brandrepo.findAll();
    }

	public Brand addBrand(Brand brand) {
		// TODO Auto-generated method stub
		return brandrepo.save(brand);
	}
   
    
//    public Brand getBrandById(int id) {
//    	Brand c_id = brandrepo.findById(id).get();
//    	return c_id;
//    }

//    public String  addBrand(Brand brand) {
//   
//    	System.out.println(brand.getCategoryid());
//    Optional<Category> cat	= catrgoryRepo.findById(brand.getCategoryid());
////    System.out.println(cat.get().getCategory_id());
//    if(cat.isPresent()) {
//    	brandrepo.save(brand);
//    	return "Saved";
//    }else {
//    return "Not Found";
//    }
//       
//    }

	public Brand updateBrand(Brand brand) {
		// TODO Auto-generated method stub
		return brandrepo.save(brand);
	}

//	public Optional<Brand> getCategoryById(int id) {
//		Optional<Brand> b = brandrepo.findById(id);
//		return b;
//	}

//	public List getCategoryById(Brand brand) {
//		List<Brand> list = brandrepo.findAll();
//		return list;
//				
//	}

	public List<Object> getProductByCategoryId(int categoryid) {
		// TODO Auto-generated method stub
		return brandrepo.findAllByCategoryid(categoryid);
	}




	public String deleteBrandById(int brandid) {
		 brandrepo.deleteById(brandid);
		 return "deleted";
	}

//	public List<Product> findBybrandname(String brandname) {
//		// TODO Auto-generated method stub
//
//		return productRepo.findAllByBrandid(brandrepo.findBybrandname(brandname).getCategoryid());
//	}

	
}
