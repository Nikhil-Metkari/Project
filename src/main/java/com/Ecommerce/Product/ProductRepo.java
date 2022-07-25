package com.Ecommerce.Product;


import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<Product,Integer> {
	
	public List<Product> findAllByBrandid( int id);
	
	public List<Object> findAllByCategoryid( int id);
	//public List<Product> findAllByBrandname( String name);
}
