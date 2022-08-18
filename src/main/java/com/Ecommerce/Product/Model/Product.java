package com.Ecommerce.Product.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Data
@Document
public class Product {

    @Id
    private int productid;
    private String name;
    private String image;
    private String details;
    private double price;
    private int categoryid;
    private int brandid;

}
