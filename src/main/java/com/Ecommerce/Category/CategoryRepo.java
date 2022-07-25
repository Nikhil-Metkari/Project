package com.Ecommerce.Category;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ecommerce.Brand.Brand;
import com.Ecommerce.Product.Product;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

	Category findBycategoryname(String categoryname);
// Optional<Category> findByCategory_id(int id);
}
