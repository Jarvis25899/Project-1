package com.example.ProductService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductDTO {

    private String productId;
    private String productName;
    private String productDesc;
    private String productCategoryId;
    private double productRating;
    private String productImage;
    private long sellCount;

}
