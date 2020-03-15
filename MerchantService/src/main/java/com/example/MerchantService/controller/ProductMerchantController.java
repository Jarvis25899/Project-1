package com.example.MerchantService.controller;

import com.example.MerchantService.dto.*;
import com.example.MerchantService.entity.Merchant;
import com.example.MerchantService.entity.ProductMerchantAvailability;
import com.example.MerchantService.response.APIResponse;
import com.example.MerchantService.service.MerchantService;
import com.example.MerchantService.service.ProductMerchantAvailabilityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/merchant")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductMerchantController {

    @Autowired
    MerchantService merchantService;

    @Autowired
    ProductMerchantAvailabilityService productMerchantAvailabilityService;


    @GetMapping("/listOfMerchant/{productId}")
    public ResponseEntity<APIResponse<List<MerchantListObj>>> listOfMerchant(@PathVariable("productId") String productId){
        return productMerchantAvailabilityService.listOfMerchant(productId);
    }

    @GetMapping("/profile/{token}")
    public ResponseEntity<APIResponse<Merchant>> getProfile(@PathVariable("token") String token){
        return new ResponseEntity<>(new APIResponse<>(1000,"SUCCESS",merchantService.getProfile(merchantService.parseToken(token))),HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<APIResponse<String>> addMerchantDetails(@RequestBody MerchantDTO merchantDTO){
        Merchant merchant = new Merchant();
        BeanUtils.copyProperties(merchantDTO,merchant);
        return merchantService.addMerchantDetails(merchant,merchantDTO.getMerchantPassword());

    }

    @PostMapping("/addProducts")
    public ResponseEntity<APIResponse<String>> addMerchantProduct(@RequestBody ProductMerchantAvailabilityDTO productMerchantAvailabilityDTO){
        ProductMerchantAvailability productMerchantAvailability = new ProductMerchantAvailability();
        BeanUtils.copyProperties(productMerchantAvailabilityDTO,productMerchantAvailability);
        String merchantId = merchantService.parseToken(productMerchantAvailabilityDTO.getToken());
        productMerchantAvailability.setMerchantId(merchantId);
        return productMerchantAvailabilityService.addMerchantProduct(productMerchantAvailability);
    }


    @GetMapping("/listOfProduct/{token}")
    public ResponseEntity<APIResponse<List<MerchantProduct>>> listOfProduct(@PathVariable("token") String token){
        return productMerchantAvailabilityService.listOfProduct(merchantService.parseToken(token));
    }


    @GetMapping("/productMerchantAvailability/{productId}/{token}")
    public ResponseEntity<APIResponse<ProductMerchantAvailability>> getProductAvailability(@PathVariable("productId") String productId, @PathVariable("token") String token){
        return new ResponseEntity<>(new APIResponse<>(1000,"SUCCESS",productMerchantAvailabilityService.find(productId,merchantService.parseToken(token))),HttpStatus.OK);
    }


    @PostMapping("/edit")
    public ResponseEntity editProduct(@RequestBody ProductMerchantAvailabilityDTO productMerchantAvailabilityDTO){
        ProductMerchantAvailability productMerchantAvailability = new ProductMerchantAvailability();
        BeanUtils.copyProperties(productMerchantAvailabilityDTO,productMerchantAvailability);
        String merchantId = merchantService.parseToken(productMerchantAvailabilityDTO.getToken());
        productMerchantAvailability.setMerchantId(merchantId);
        productMerchantAvailabilityService.editProduct(productMerchantAvailability);

        return new ResponseEntity<>(new APIResponse<>(1000,"SUCCESS"),HttpStatus.OK);
    }

    @PostMapping("/updateStock")
    public void updateStock(@RequestBody SearchDTO searchDTO){
        productMerchantAvailabilityService.updateStock(searchDTO);
    }

    @GetMapping("/getMerchantDetails/{merchantId}")
    public Merchant getMerchantDetails(@PathVariable("merchantId") String merchantId){
        return merchantService.getMerchant(merchantId);
    }

    @GetMapping("/productAvailability/{productId}/{merchantId}")
    public ProductMerchantAvailability get(@PathVariable("productId") String productId, @PathVariable("merchantId") String merchantId){
        return productMerchantAvailabilityService.find(productId,merchantId);
    }


    @PostMapping("/saveMerchantDetails")
    public String saveMerchant(@RequestBody MerchantDTO merchantDTO){
        Merchant merchant = new Merchant();
        BeanUtils.copyProperties(merchantDTO,merchant);
        return merchantService.saveMerchant(merchant);
    }
}
