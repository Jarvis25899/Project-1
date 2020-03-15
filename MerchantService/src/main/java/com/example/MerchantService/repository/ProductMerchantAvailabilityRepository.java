package com.example.MerchantService.repository;

import com.example.MerchantService.entity.ProductMerchantAvailability;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ProductMerchantAvailabilityRepository extends CrudRepository<ProductMerchantAvailability,Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE ProductMerchantAvailability SET quantity = :#{#quantity} , price = :#{#price} WHERE product_id = :#{#productId} AND merchant_id = :#{#merchantId}")
    void editProduct(@Param("quantity") long quantity, @Param("price") double price, @Param("productId") String productId, @Param("merchantId") String merchantId);

    @Query("SELECT pma FROM ProductMerchantAvailability pma WHERE product_id = ?1 AND merchant_id = ?2")
    ProductMerchantAvailability find(String productId,String merchantId);
}
