package com.example.ProductService.service.impl;

import com.example.ProductService.document.Product;
import com.example.ProductService.repository.ProductRepository;
import com.example.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product addProductDetails(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product productDetails(String id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> find(String categoryId) {
        List<Product> productList = productRepository.findAll();
        List<Product> productCategoryList = new ArrayList<>();
        productList.stream().forEach(product -> {
            if ((product.getProductCategoryId()).equals(categoryId)){
                productCategoryList.add(product);
            }
        });
        return productCategoryList;
    }

    @Override
    public void updateSellCount(String productId,long sellCount) {
        Product product = productRepository.findById(productId).get();
        product.setSellCount(sellCount);
        productRepository.save(product);
    }
}
