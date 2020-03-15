package com.example.MerchantService.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Merchant")
@Getter @Setter
public class Merchant {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String merchantId;

    private String merchantName;
    private String mobileNo;
    private double merchantRating;
    private String merchantAddress;

    private String merchantEmail;

    private String merchantImage;

}
