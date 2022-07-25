package com.Ecommerce.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepo extends MongoRepository<Category,Integer> {

	Category findBycategoryname(String categoryname);

	boolean findByCategoryenabled(Category category);
// Optional<Category> findByCategory_id(int id);
}
