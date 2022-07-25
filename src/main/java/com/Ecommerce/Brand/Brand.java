package com.Ecommerce.Brand;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
public class Brand {

    @Id
    private int brandid;
    private String brandname;
    private String brandlogo;
    private int categoryid;


    
    
}
