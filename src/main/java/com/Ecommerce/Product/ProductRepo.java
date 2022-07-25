package com.Ecommerce.Product;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;

public interface ProductRepo extends JpaRepository<Product,Integer> {
	
	public List<Product> findAllByBrandid( int id);
	
	public List<Object> findAllByCategoryid( int id);
	//public List<Product> findAllByBrandname( String name);
}
