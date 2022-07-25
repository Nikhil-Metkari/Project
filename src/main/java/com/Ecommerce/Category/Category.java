package com.Ecommerce.Category;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
public class Category {

    @Id
    private int categoryid;
    private String categoryname;
    private boolean categoryenabled;

    
  
}
