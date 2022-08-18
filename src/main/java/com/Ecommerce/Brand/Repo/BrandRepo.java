package com.Ecommerce.Brand.Repo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.Ecommerce.Brand.Model.Brand;


public interface BrandRepo extends MongoRepository<Brand,Integer> {


	Brand findBybrandname(String n);
	
	public List<Object> findAllByCategoryid( int id);
	
	public List<Object> findAllByBrandid( int id);

	
	//List<Brand> getBycategory_enabled(boolean category_enabled);
	//Optional<Brand> getCategoryById(int id );
}
