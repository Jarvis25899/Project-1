package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UpdateDto {

 private String productId;
    private String productName;
    private String productDesc;
    private String productCategoryId;
    private double productRating;
    private String productImage;
    //private String productImageThumbnail;
    private long sellCount;
    private List<String> merchantId;
    private List<Long> quantity;
    private List<Double> price;


    @Override
    public String toString() {
        return "UpdateDto{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", productCategoryId='" + productCategoryId + '\'' +
                ", productRating=" + productRating +
                ", productImage='" + productImage + '\'' +
                ", sellCount=" + sellCount +
                ", merchantId=" + merchantId +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}

