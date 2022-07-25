package com.Ecommerce.Product;

import javax.persistence.*;

import lombok.Data;


@Data
@Entity
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
