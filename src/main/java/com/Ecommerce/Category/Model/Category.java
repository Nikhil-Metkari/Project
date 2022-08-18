package com.Ecommerce.Category.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Category {

	@Id
	private int categoryid;
	private String categoryname;
	private boolean categoryenabled;


}
