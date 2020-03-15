package com.example.ProductService.service;

import com.example.ProductService.document.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(Category category);
    List<Category> findAll();
}
