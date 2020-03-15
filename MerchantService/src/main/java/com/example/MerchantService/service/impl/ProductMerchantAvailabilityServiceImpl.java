package com.example.MerchantService.service.impl;

import com.example.MerchantService.dto.MerchantListObj;
import com.example.MerchantService.dto.MerchantProduct;
import com.example.MerchantService.dto.Product;
import com.example.MerchantService.dto.SearchDTO;
import com.example.MerchantService.entity.Merchant;
import com.example.MerchantService.entity.ProductMerchantAvailability;
import com.example.MerchantService.proxy.ProductProxy;
import com.example.MerchantService.repository.MerchantRepository;
import com.example.MerchantService.repository.ProductMerchantAvailabilityRepository;
import com.example.MerchantService.response.APIResponse;
import com.example.MerchantService.service.ProductMerchantAvailabilityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProductMerchantAvailabilityServiceImpl implements ProductMerchantAvailabilityService {

    @Autowired
    ProductProxy productProxy;

    @Autowired
    ProductMerchantAvailabilityRepository productMerchantAvailabilityRepository;

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public ResponseEntity<APIResponse<String>> addMerchantProduct(ProductMerchantAvailability productMerchantAvailability) {

        Product product = productProxy.getProductDetails(productMerchantAvailability.getProductId()).getBody().getData();
        AtomicInteger flag = new AtomicInteger(0);
        List<ProductMerchantAvailability> list = (List<ProductMerchantAvailability>) productMerchantAvailabilityRepository.findAll();
        list.stream().forEach(productMerchant -> {
            if ((productMerchant.getProductId()).equals(productMerchantAvailability.getProductId()) && (productMerchant.getMerchantId()).equals(productMerchantAvailability.getMerchantId())){
                flag.set(1);
                //productMerchantAvailabilityRepository.editProduct(productMerchantAvailability.getQuantity(),productMerchantAvailability.getPrice(),product.getProductId(),productMerchantAvailability.getMerchantId());
            }
        });

        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setMerchantId(productMerchantAvailability.getMerchantId());
        searchDTO.setProductId(product.getProductId());
        searchDTO.setProductCategoryId(product.getProductCategoryId());
        searchDTO.setProductDesc(product.getProductDesc());
        searchDTO.setProductImage(product.getProductImage());
        searchDTO.setProductName(product.getProductName());
        searchDTO.setProductRating(product.getProductRating());
        searchDTO.setQuantity(productMerchantAvailability.getQuantity());
        searchDTO.setPrice(productMerchantAvailability.getPrice());
        searchDTO.setSellCount(product.getSellCount());

        if (flag.get()==0) {
            productMerchantAvailabilityRepository.save(productMerchantAvailability);
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                kafkaTemplate.send("SolrData",objectMapper.writeValueAsString(searchDTO));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(new APIResponse<>(1000,"SUCCESS","Successfully added"),HttpStatus.OK);
        }

        return new ResponseEntity<>(new APIResponse<>(1000,"SUCCESS","Already Exists!!"),HttpStatus.OK);

    }

    @Override
    public void editProduct(ProductMerchantAvailability productMerchantAvailability) {
        productMerchantAvailabilityRepository.editProduct(productMerchantAvailability.getQuantity(),productMerchantAvailability.getPrice(),productMerchantAvailability.getProductId(),productMerchantAvailability.getMerchantId());
    }

    @Override
    public ProductMerchantAvailability find(String productId, String merchantId) {
        return productMerchantAvailabilityRepository.find(productId,merchantId);
    }

    @Override
    public ResponseEntity<APIResponse<List<MerchantProduct>>> listOfProduct(String merchantId) {
        List<ProductMerchantAvailability> list = (List<ProductMerchantAvailability>) productMerchantAvailabilityRepository.findAll();

        List<MerchantProduct> productList = new ArrayList<>();

        list.stream().forEach(productMerchantAvailability -> {
            if ((productMerchantAvailability.getMerchantId()).equals(merchantId)){
                Product product = productProxy.getProductDetails(productMerchantAvailability.getProductId()).getBody().getData();
                MerchantProduct merchantProduct = new MerchantProduct();
                merchantProduct.setMerchantId(merchantId);
                merchantProduct.setProductId(productMerchantAvailability.getProductId());
                merchantProduct.setProductName(product.getProductName());
                merchantProduct.setPrice(productMerchantAvailability.getPrice());
                merchantProduct.setQuantity(productMerchantAvailability.getQuantity());
                merchantProduct.setProductImage(product.getProductImage());

                productList.add(merchantProduct);
            }
        });
        return new ResponseEntity<>(new APIResponse<>(1000,"SUCCESS",productList),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<List<MerchantListObj>>> listOfMerchant(String productId) {
        List<MerchantListObj> merchantList = new ArrayList<>();
        List<ProductMerchantAvailability> merchantProductList = (List<ProductMerchantAvailability>) productMerchantAvailabilityRepository.findAll();
        AtomicInteger flag = new AtomicInteger(0);
        merchantProductList.stream().forEach(merchantProducts -> {
            if ((merchantProducts.getProductId()).equals(productId)){
                Merchant merchant = merchantRepository.findById(merchantProducts.getMerchantId()).get();
                MerchantListObj merchantListObj = new MerchantListObj();
                merchantListObj.setMerchantId(merchantProducts.getMerchantId());
                merchantListObj.setMerchantName(merchant.getMerchantName());
                merchantListObj.setMerchantRating(merchant.getMerchantRating());
                merchantListObj.setPrice(merchantProducts.getPrice());
                merchantListObj.setQuantity(merchantProducts.getQuantity());

                merchantList.add(merchantListObj);
                flag.set(1);
            }
        });
        Collections.sort(merchantList,Collections.reverseOrder(new Comparator<MerchantListObj>() {
            @Override
            public int compare(MerchantListObj o1, MerchantListObj o2) {
                if (o1.getMerchantRating() < o2.getMerchantRating())
                    return -1;
                else if (o1.getMerchantRating() == o2.getMerchantRating()){
                    if (o1.getPrice() < o2.getPrice())
                        return -1;
                    else
                        return 1;
                }
                else
                    return 1;
            }
        }));
        if (flag.get() == 0)
            return new ResponseEntity<>(new APIResponse<>(400,"Sorry!! Not any merchant is selling this product"),HttpStatus.OK);

        return new ResponseEntity<>(new APIResponse<>(1000,"SUCCESS",merchantList),HttpStatus.OK);
    }

    @Override
    public void updateStock(SearchDTO searchDTO) {
        long updatedQuantity = 0 - searchDTO.getQuantity();

        ProductMerchantAvailability productMerchantAvailability = productMerchantAvailabilityRepository.find(searchDTO.getProductId(),searchDTO.getMerchantId());
        long quantity = productMerchantAvailability.getQuantity() - searchDTO.getQuantity();
        System.out.println(quantity);
        productMerchantAvailabilityRepository.editProduct(quantity,productMerchantAvailability.getPrice(),productMerchantAvailability.getProductId(),productMerchantAvailability.getMerchantId());
        searchDTO.setQuantity(updatedQuantity);


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            kafkaTemplate.send("SolrData",objectMapper.writeValueAsString(searchDTO));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
