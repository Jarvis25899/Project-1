package com.example.MerchantService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MerchantListObj {

    private String merchantId;
    private String merchantName;
    private double merchantRating;
    private long quantity;
    private double price;
}
