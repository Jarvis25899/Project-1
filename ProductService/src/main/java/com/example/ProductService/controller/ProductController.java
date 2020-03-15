package com.example.ProductService.controller;


import com.example.ProductService.document.Category;
import com.example.ProductService.document.Product;
import com.example.ProductService.dto.CategoryDTO;
import com.example.ProductService.dto.ProductDTO;
import com.example.ProductService.response.APIResponse;
import com.example.ProductService.service.CategoryService;
import com.example.ProductService.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;



    @GetMapping("/message")
    public String print(){
        System.out.println("product instance running");
        return "this is from port no 8080";
    }

    @PostMapping("/addProduct")
    public ResponseEntity<APIResponse<String>> addProductDetails(@RequestBody ProductDTO productDTO){
        Product product = new Product();
        BeanUtils.copyProperties(productDTO,product);
        Product productCreated = productService.addProductDetails(product);
        return new ResponseEntity<>(new APIResponse<>(1000,"SUCCESS","Product successfully added"),HttpStatus.OK);
    }

    @PostMapping("/addCategory")
    public ResponseEntity<APIResponse<String>> addCategory(@RequestBody CategoryDTO categoryDTO){
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        Category categoryCreated = categoryService.addCategory(category);

        return new ResponseEntity<>(new APIResponse<>(1000,"SUCCESS","Category successfully added"),HttpStatus.OK);
    }

    @GetMapping("/allCategories")
    public ResponseEntity<APIResponse<List<Category>>> categoryList(){
        return new ResponseEntity<>(new APIResponse<>(1000,"SUCCESS",categoryService.findAll()),HttpStatus.OK);
    }

    @GetMapping("/allProducts/{categoryId}")
    public ResponseEntity<APIResponse<List<Product>>> productList(@PathVariable("categoryId") String categoryId){
        return new ResponseEntity<>(new APIResponse<>(1000,"SUCCESS",productService.find(categoryId)),HttpStatus.OK);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<APIResponse<Product>> getProductDetails(@PathVariable("id") String id){
        return new ResponseEntity<>(new APIResponse<>(1000,"SUCCESS",productService.productDetails(id)),HttpStatus.OK);
    }

    @GetMapping("/updateSellCount/{productId}/{sellCount}")
    public void updateSellCount(@PathVariable("productId") String productId,@PathVariable("sellCount") long sellCount){
        productService.updateSellCount(productId,sellCount);
    }
}
