package com.example.MerchantService.service;

import com.example.MerchantService.dto.MerchantListObj;
import com.example.MerchantService.dto.MerchantProduct;
import com.example.MerchantService.dto.SearchDTO;
import com.example.MerchantService.entity.ProductMerchantAvailability;
import com.example.MerchantService.response.APIResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductMerchantAvailabilityService {
    ResponseEntity<APIResponse<String>> addMerchantProduct(ProductMerchantAvailability productMerchantAvailability);
    void editProduct(ProductMerchantAvailability productMerchantAvailability);
    ProductMerchantAvailability find(String productId,String merchantId);
    ResponseEntity<APIResponse<List<MerchantProduct>>> listOfProduct(String merchantId);
    ResponseEntity<APIResponse<List<MerchantListObj>>> listOfMerchant(String productId);
    void updateStock(SearchDTO searchDTO);
}
