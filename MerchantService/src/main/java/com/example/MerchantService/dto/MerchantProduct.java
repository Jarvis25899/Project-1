package com.example.MerchantService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MerchantProduct {

    private String merchantId;
    private String productId;
    private String productName;
    private String productImage;
    private long quantity;
    private double price;
}
