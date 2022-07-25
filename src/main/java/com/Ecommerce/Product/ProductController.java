package com.Ecommerce.Product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommerce.Brand.Brand;
import com.Ecommerce.Brand.BrandRepo;
import com.Ecommerce.Brand.BrandService;
import com.Ecommerce.Category.CategoryRepo;
import com.Ecommerce.Category.CategoryService;
import com.Ecommerce.Exception.InvalidEntry;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
   private ProductService productservice;
    
    Logger log = LoggerFactory.getLogger(CategoryService.class);
    
    @Autowired
    private ProductRepo productrepo;
    
    @Autowired
    private BrandRepo brandrepo;

    @Autowired
    private CategoryRepo categoryRepo;
    

    @GetMapping("/Ecommerce/Products/all")
    public List<Product> getProduct()
    {
    	List<Product> list = productservice.getProduct();
        if(list.isEmpty())
    	{
    		log.warn("List is empty");
    		throw new InvalidEntry ("Given List is empty"); 
    	}
        return list;
        
    }

    @PostMapping("/Ecommerce/Products/add")
    public Product addProduct(@RequestBody Product product)
    {
    	
    	if(categoryRepo.existsById(product.getCategoryid()))
    	{
    		if(brandrepo.existsById(product.getBrandid()))
    		{
    			if(!productrepo.existsById(product.getProductid())) {
    			return  productservice.addProduct(product);
    			}
    			else {
    				log.warn("Passed product id is exists enter another product id .");
        			throw new  InvalidEntry("Passed product id is exists enter another nummber .");
    			}
    		}
    		else {
    			log.warn("Wrong Brand id passsed.");
    			throw new  InvalidEntry("Wrong Brand id passsed.");
    			
    		}
    	}
    	else {
    		log.warn("Wrong Category id passsed.");
			throw new  InvalidEntry("Wrong Category id passsed.");
    	}
       
    }
    
    @PostMapping("/Ecommerce/Products/update/byproductid/")
    public Product updateProduct(@RequestBody Product product)
    {
    	Optional<Product> po = productrepo.findById(product.getProductid());
    	if(po.isPresent())
    	{
    	if(!categoryRepo.existsById(product.getCategoryid()))
    	{
    		log.warn("Category ID  is not present");
			throw new  InvalidEntry("Given Category ID  is not present.");
    	}
    	else
    	{
    		if(!brandrepo.existsById(product.getBrandid()))
    		{
    			log.warn("Wrong Brand id entered");
    			throw new  InvalidEntry("Given Brand_ID is not present.");
    		}
    		else {
    			return productservice.updateProduct(product);
    		}
    	}
    	}
    else {
		log.warn("Product ID is not present");
		throw new  InvalidEntry("Given Product ID  is not present.");
    }
}
    
    
    
    @GetMapping ("/Ecommerce/Products/list/bybrandid/{brandid}")
    public List<Product> getProductByBrandId(@PathVariable int brandid)
    {
    	System.out.println(brandid);
    	if(brandrepo.findById(brandid).isEmpty())
    	{
    		log.warn("Wrong Brand id");
  	        throw new InvalidEntry("Wrong  brand id passed");
  		}
  		else {
  			return productservice.getProductByBrandId(brandid);
  		}
    }
    
    @PostMapping("/Ecommerce/Products/delete/byproductid/")
    public String deleteProduct(@RequestBody Product product)
    {
    	if(!productrepo.findById(product.getProductid()).isEmpty())
    	{
			 productservice.deleteProduct(product.getProductid());
			 return "Deleted";
		}
    	else {
    		log.warn("Wrong product id entered");
			throw new InvalidEntry("Wrong product id passed");
    	}
   
    }
    
    
    
    
    
    
    
}