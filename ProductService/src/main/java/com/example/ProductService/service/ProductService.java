package com.example.ProductService.service;

import com.example.ProductService.document.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product addProductDetails(Product product);
    Product productDetails(String id);
    List<Product> find(String categoryId);
    void updateSellCount(String productId,long sellCount);
}
