package com.Ecommerce.Brand;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BrandRepo extends MongoRepository<Brand,Integer> {


	Brand findBybrandname(String n);
	
	public List<Object> findAllByCategoryid( int id);
	
	public List<Object> findAllByBrandid( int id);

	
	//List<Brand> getBycategory_enabled(boolean category_enabled);
	//Optional<Brand> getCategoryById(int id );
}
