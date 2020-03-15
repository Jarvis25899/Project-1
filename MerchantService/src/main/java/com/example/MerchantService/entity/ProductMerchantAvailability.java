package com.example.MerchantService.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ProductMerchantAvailability")
@Getter @Setter
public class ProductMerchantAvailability {

    @Id
    @GenericGenerator(name = "idGenerator" , strategy = "increment")
    @GeneratedValue(generator = "idGenerator")
    private int ProductMerchantId;

    private String productId;
    private String merchantId;
    private long quantity;
    private double price;

}
