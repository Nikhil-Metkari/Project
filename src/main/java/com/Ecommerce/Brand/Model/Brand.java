package com.Ecommerce.Brand.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Brand {

    @Id
    private int brandid;
    private String brandname;
    private String brandlogo;
    private int categoryid;


    
    
}
