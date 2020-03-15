package com.example.ProductService.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Product")
@Getter @Setter
public class Product {

    @Id
    private String productId;

    private String productName;
    private String productDesc;
    private String productCategoryId;
    private double productRating;
    private String productImage;
    private long sellCount;
}
