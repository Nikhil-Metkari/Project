package com.Ecommerce.Product.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommerce.Product.Model.Product;
import com.Ecommerce.Product.Repo.ProductRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productrepo;

    public List<Product> getProduct() {
        return productrepo.findAll();

    }

    public Product addProduct(Product product) {

        return productrepo.save(product);
    }



	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		return productrepo.save(product);
	}

	public List <Product> getProductByBrandId(int id) {
		// TODO Auto-generated method stub
		return productrepo.findAllByBrandid(id);
	}

	public List<Object> getProductByCategoryId(int categoryid) {
		// TODO Auto-generated method stub
		return productrepo.findAllByCategoryid(categoryid);
	}


	public String deleteProduct(int productid) {
		 productrepo.deleteById(productid);
		return "deleted";
	}




}
