package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SolrDocument(collection = "search")
public class SearchEntity {
    @Id
    @Indexed(name = "productId", type = "string")
    private String productId;
    @Field
    private String productName;
    @Field
    private String productDesc;
    @Field
    private String productCategoryId;
    @Field
    private double productRating;
    @Field
    private String productImage;
    @Field
    private long sellCount;
    @Field
    private String merchantId;
    @Field
    private long quantity;
    @Field
    private double price;

}
