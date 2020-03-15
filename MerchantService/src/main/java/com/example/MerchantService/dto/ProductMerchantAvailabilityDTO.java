package com.example.MerchantService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductMerchantAvailabilityDTO {

    private int ProductMerchantId;
    private String productId;
    private String token;
    private String merchantId;
    private long quantity;
    private double price;

}
