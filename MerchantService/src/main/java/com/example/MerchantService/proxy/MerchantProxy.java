package com.example.MerchantService.proxy;

import com.example.MerchantService.dto.MerchantDTO;
import com.example.MerchantService.response.APIResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("loginservice")
public interface MerchantProxy {

    @PostMapping("/saveMerchantLoginDetails")
    ResponseEntity<APIResponse<String>> loginMerchant(@RequestBody MerchantDTO merchantDTO);
}
