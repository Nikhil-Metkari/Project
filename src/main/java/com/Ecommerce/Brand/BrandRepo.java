package com.Ecommerce.Brand;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BrandRepo extends JpaRepository<Brand,Integer> {


	Brand findBybrandname(String n);
	
	public List<Object> findAllByCategoryid( int id);
	
	public List<Object> findAllByBrandid( int id);

	
	//List<Brand> getBycategory_enabled(boolean category_enabled);
	//Optional<Brand> getCategoryById(int id );
}
