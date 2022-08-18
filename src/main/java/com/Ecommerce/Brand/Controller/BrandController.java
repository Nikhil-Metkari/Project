package com.Ecommerce.Brand.Controller;


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

import com.Ecommerce.Brand.Model.Brand;
import com.Ecommerce.Brand.Repo.BrandRepo;
import com.Ecommerce.Brand.Service.BrandService;
import com.Ecommerce.Category.Repo.CategoryRepo;
import com.Ecommerce.Category.Service.CategoryService;
import com.Ecommerce.Exception.InvalidEntry;
import com.Ecommerce.Product.Model.Product;
import com.Ecommerce.Product.Repo.ProductRepo;
import com.Ecommerce.Product.Service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
public class BrandController {

	Logger log = LoggerFactory.getLogger(CategoryService.class);
	
	@Autowired
    private CategoryRepo categoryRepo;
    @Autowired
   private BrandRepo brandrepo;
	
    @Autowired
    private BrandService brandservice;
    
    @Autowired
    private ProductService productservice;
    
    @Autowired
    private ProductRepo productrepo;

    @GetMapping("/Ecommerce/Brands/all")
    public List<Brand> getBrand()
    {
    	List<Brand> list = brandservice.getBrand();
    	if(list.isEmpty())
    	{
    		log.warn("List is empty");
    		throw new InvalidEntry ("Given List is empty"); 
    	}
        return list;
    }

//  @PostMapping("/Ecommerce/Brands/add")
//  public String addBrand(@RequestBody Brand brand)
//  {
//        return brandservice.addBrand(brand);
//  }
    
    @PostMapping("/Ecommerce/Brands/add")
    public Brand addBrand(@RequestBody Brand brand)
    {
    	if(!categoryRepo.existsById(brand.getCategoryid()))
        {
    	  log.warn("Wrong category id or not present");
       	throw new InvalidEntry("Category id is empty or wrong id passed.");
    	 }
       else {
//		   if(categoryRepo.findById(brand.getCategoryid()).get().isCategoryenabled())
//    	   {
			   return brandservice.addBrand(brand);
//		   }else {
//	
//			   log.warn("Categoty is not enabled");
//			   throw new InvalidEntry("Given categoty is not enabled.");
//		   }
		   }
       }

    
  
  @PutMapping("/Ecommerce/Brands/Update/")
  public Brand updateBrand(@RequestBody Brand brand)
  {
	//  Optional<Brand> b = brandrepo.findById(brand.getBrandid());
	  if(brandrepo.existsById(brand.getBrandid()))
	  {
		  if(!categoryRepo.existsById(brand.getCategoryid()))
		  {
			  log.warn("Wrong Category_id entered");
				throw new InvalidEntry("Wrong Category id passed"); 
		  }
		  else {
		  return brandservice.updateBrand(brand);
		  }
	  }
	  else {
			log.warn("Wrong brand_id entered");
			throw new InvalidEntry("Wrong Brand id passed");
	  }
  }
  
  @GetMapping("/Ecommerce/Products/list/bybrandname/{brandname}")
  public List<Product> getProductByBrandname(@PathVariable String brandname)
  {
		int brandid = brandrepo.findBybrandname(brandname).getBrandid();
	  	if(!brandrepo.findById(brandid).isEmpty())
	  	{
			return productservice.getProductByBrandId(brandid);
	  	
			}
			else {
			log.warn("Wrong Brand name");
			throw new InvalidEntry("Wrong  brand name passed");
		}
 }

	@GetMapping ("/Ecommerce/Products/list/bybrandid/{brandid}")
	public List<Product> getProductByBrandId(@PathVariable int brandid)
	{
		//System.out.println(brandid);
		if(brandrepo.findById(brandid).isEmpty())
		{
			log.warn("Wrong Brand id");
			throw new InvalidEntry("Wrong  brand id passed");
		}
		else {
			return productservice.getProductByBrandId(brandid);
		}
	}
  

	@DeleteMapping("/Ecommerce/Brands/deletebyid/")
		public String deleteBrandById(@RequestBody Brand brand)
		{
		
		if(brandrepo.existsById(brand.getBrandid()))
				{
				if(productrepo.findAllByBrandid(brand.getBrandid()).isEmpty())
				{
					brandservice.deleteBrandById(brand.getBrandid());
					return  "Deleted";
				}
				else {
					log.warn("First delete products of given brand");
					throw new InvalidEntry("First delete Rows of  product details of given brand id.");
				}
				}
		else {
			log.warn("Wrong Brand id entered");
			throw new InvalidEntry("Wrong Brand id passed");
		}
				
		}
  
  
	 @PostMapping("/Ecommerce/Allist/bybody/id")
	  public List<Product> getProductByBody(@RequestBody Brand b)
	  {
		 int a = b.getBrandid();
		 
		 if( a==0 ) {
		    		log.warn(" Brand id is null");
		  	        throw new InvalidEntry("Brand id is null");
		 } 
		 else {
			 if(brandrepo.existsById(a))
			 {
				   List<Product> p1 = productservice.getProductByBrandId(a);
			       return p1;	  
			 }
			 else {
				 log.warn("Wrong brand id");
		  	        throw new InvalidEntry("Wrong  brand id passed");
			 }
		 }
		 }
		          
		 @PostMapping("/Ecommerce/Allist/bybody/name")
		  public List<Product>getProductByBodyName(@RequestBody Brand br)
		  {
			 String str = br.getBrandname();
			 
			 if(str==null) {
			    		log.warn("Brand name  is null");
			  	        throw new InvalidEntry("Brand name is null");
			 } 
			 else {

				 if(brandrepo.findBybrandname(str)!=null)
				 {
					 int brandid = brandrepo.findBybrandname(str).getBrandid();
						
					   List<Product> p1 = productservice.getProductByBrandId(brandid);
				         return p1;
				 }
				 else {
					 log.warn("Wrong Brand name passed");
			  	        throw new InvalidEntry("Wrong Brand name passed");
				 }
				 }
		                  
	  }    
	
  
  
  
  
  

}