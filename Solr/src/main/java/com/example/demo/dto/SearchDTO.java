package com.example.demo.dto;
/*
product_id 			string
product_name			string
product_desc			string
product_category _id		string
product_rating 		double
product_image		string
product_image_thumbnail	string
sell_count			long
merchant_id			string
quantity			long
price				double

 */

import lombok.*;
import org.apache.solr.client.solrj.beans.Field;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SearchDTO {

    private String productId;
    private String productName;
    private String productDesc;
    private String productCategoryId;
    private double productRating;
    private String productImage;
    private long sellCount;
    private String merchantId;
    private long quantity;
    private double price;

    @Override
    public String toString() {
        return "SearchDTO{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", productCategoryId='" + productCategoryId + '\'' +
                ", productRating=" + productRating +
                ", productImage='" + productImage + '\'' +
                ", sellCount=" + sellCount +
                ", merchantId='" + merchantId + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
