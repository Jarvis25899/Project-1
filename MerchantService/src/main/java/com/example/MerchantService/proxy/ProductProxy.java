package com.example.MerchantService.proxy;

import com.example.MerchantService.dto.Product;
import com.example.MerchantService.response.APIResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("productservice")
public interface ProductProxy {

    @GetMapping("/product/details/{id}")
    ResponseEntity<APIResponse<Product>> getProductDetails(@PathVariable("id") String id);
}
