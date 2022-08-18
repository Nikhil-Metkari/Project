package com.Ecommerce.Category.Repo;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.Ecommerce.Category.Model.Category;

public interface CategoryRepo extends MongoRepository<Category,Integer> {

	Category findBycategoryname(String categoryname);

	boolean findByCategoryenabled(Category category);
// Optional<Category> findByCategory_id(int id);
}
