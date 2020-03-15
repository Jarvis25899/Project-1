package com.example.MerchantService.service;

import com.example.MerchantService.dto.MerchantDTO;
import com.example.MerchantService.entity.Merchant;
import com.example.MerchantService.response.APIResponse;
import org.springframework.http.ResponseEntity;

public interface MerchantService {
    String parseToken(String token);
    ResponseEntity<APIResponse<String>> addMerchantDetails(Merchant merchant, String merchantPassword);
    Merchant getProfile(String id);
    MerchantDTO merchantResponse(Merchant merchant, String password);
    Merchant getMerchant(String merchantId);
    String saveMerchant(Merchant merchant);
}
