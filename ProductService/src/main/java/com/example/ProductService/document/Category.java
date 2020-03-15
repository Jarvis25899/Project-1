package com.example.ProductService.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Category")
@Getter @Setter
public class Category {

    @Id
    private String productCategoryId;

    private String productCategoryName;
}
