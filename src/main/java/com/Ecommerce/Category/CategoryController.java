package com.Ecommerce.Category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommerce.Brand.BrandRepo;
import com.Ecommerce.Brand.BrandService;
import com.Ecommerce.Exception.InvalidEntry;
import com.Ecommerce.Product.ProductRepo;
import com.Ecommerce.Product.ProductService;

import java.util.List;

@RestController
public class CategoryController {

	Logger log = LoggerFactory.getLogger(CategoryService.class);
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ProductRepo productrepo;

	@Autowired
	private BrandRepo brandrepo;


	@Autowired
	private BrandService brandservice;

	@Autowired
	private ProductService productservice;

	@GetMapping("/Ecommerce/Category/all")
	public List<Category> getallCategory()
	{
		List<Category> list = categoryService.getallcategory();
		if(list.isEmpty())
		{
			log.warn("List is empty");
			throw new InvalidEntry ("Given List is empty");
		}
		return list;
	}

	@PostMapping("/Ecommerce/Category/add")
	public Category  addCategory(@RequestBody Category category)
	{
		Category co = categoryService.addCategory(category);
		if(category.getCategoryid()==0L)
		{
			log.warn("Category id not present");
			throw new InvalidEntry("Category id is empty");
		}
		else {
			return co;

		}
	}

	@DeleteMapping("/Ecommerce/Category/deletebyid/")
	public String deleteCategoryById(@RequestBody Category category)
	{
		if(categoryRepo.existsById(category.getCategoryid()))
		{
			if(productrepo.findAllByCategoryid(category.getCategoryid()).isEmpty())
			{
				if(brandrepo.findAllByCategoryid(category.getCategoryid()).isEmpty())
				{
					categoryService.deleteCategoryById(category.getCategoryid());
					return  "Deleted";
				}
				else {
					log.warn("First delete brand details of given category");
					throw new InvalidEntry("First delete Rows of  brand details of given category");
				}
			}else {
				log.warn("First delete product of given category");
				throw new InvalidEntry("First delete Rows of product of given category");
			}
		}
		else {
			log.warn("Wrong Category id entered");
			throw new InvalidEntry("Wrong category id passed");
		}

	}



	@GetMapping("/Ecommerce/Products/list/bycategoryid/{id}")
	public List<Object> getProductByCategoryId(@PathVariable int id)
	{

		if(!categoryRepo.existsById(id))
		{
			log.warn("Wrong Category id");
			throw new InvalidEntry("Wrong  Category id passed");
		}
		else {
			List<Object> p = productservice.getProductByCategoryId(id);
			List<Object> p1 = brandservice.getProductByCategoryId(id);
			p1.add(p);
			return p1;
		}
	}

	@GetMapping("/Ecommerce/Allist/bycategoryname/{categoryname}")
	public List<Object> getProductByBrandname(@PathVariable String categoryname)
	{

		if(categoryname!=null)
		{
			int categoryid = categoryRepo.findBycategoryname(categoryname).getCategoryid();
			if (categoryRepo.findBycategoryname(categoryname)!=null)
			{
				if(categoryRepo.findById(categoryid).isEmpty())
				{
					log.warn("Wrong Category name");
					throw new InvalidEntry("Wrong  Category name passed");
				}
				else {
					List<Object> p1 = productservice.getProductByCategoryId(categoryid);
					List<Object> p2 = brandservice.getProductByCategoryId(categoryid);
					p1.add(p2);
					return p1;
				}

			}
			else {
				log.warn("Name is wrong ");
				throw new InvalidEntry("Wrong name passed");
			}
		}
		else {
			log.warn("Category name is null");
			throw new InvalidEntry("Category name is null");
		}
	}


	@PostMapping("/Ecommerce/Allist/bybody")
	public List<Object> getProductByBody(@RequestBody Category c)
	{
		int a = c.getCategoryid();
		String str = c.getCategoryname();

		if( a==0 || str==null) {
			log.warn(" Category name or category id is null");
			throw new InvalidEntry("Category name or category id is null");
		}
		else {
			if(categoryRepo.existsById(a))
			{
				if(categoryRepo.findBycategoryname(str)!=null)
				{
					int categoryid = categoryRepo.findBycategoryname(str).getCategoryid();

					List<Object> p2 = productservice.getProductByCategoryId(categoryid);
					List<Object> p1 = brandservice.getProductByCategoryId(categoryid);

					p1.add(p2);
					if(categoryid!=a) {
						log.warn("Wrong Category name and id is not matching");
						throw new InvalidEntry("Wrong  Category name and category id is not matching");
					}
					else {
						return p1;
					}
				}
				else {
					log.warn("Wrong Category name");
					throw new InvalidEntry("Wrong  Category name passed");
				}
			}
			else {
				log.warn("Wrong Category id");
				throw new InvalidEntry("Wrong  Category id passed");
			}

		}



	}








}






