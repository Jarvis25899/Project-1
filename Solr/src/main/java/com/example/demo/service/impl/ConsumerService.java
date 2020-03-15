package com.example.demo.service.impl;

import com.example.demo.dto.SearchDTO;
import com.example.demo.entity.SearchEntity;
import com.example.demo.repository.SearchRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsumerService {

    @Autowired
    SearchRepository searchRepository;

    @KafkaListener(topics = "SolrData",groupId = "group-id",containerFactory = "kafkaListenerContainerFactory")
    public void consume(String searchDTOString){
        ObjectMapper objectMapper = new ObjectMapper();
        SearchDTO searchDTO = new SearchDTO();
        try {
            searchDTO = objectMapper.readValue(searchDTOString,SearchDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SearchEntity searchObj = new SearchEntity();
        SearchEntity searchEntity = searchRepository.findByProductId(searchDTO.getProductId());
        if (searchEntity == null){
            System.out.println(searchDTO);
            SearchEntity search = new SearchEntity();
            BeanUtils.copyProperties(searchDTO,search);
            searchRepository.save(search);
        }
        else {
            System.out.println(searchDTO);
            if (searchDTO.getPrice() < searchEntity.getPrice()) {
                searchObj.setMerchantId(searchDTO.getMerchantId());
                searchObj.setPrice(searchDTO.getPrice());
            } else {
                searchObj.setMerchantId(searchEntity.getMerchantId());
                searchObj.setPrice(searchEntity.getPrice());
            }
            searchObj.setQuantity(searchEntity.getQuantity() + searchDTO.getQuantity());

            searchObj.setProductCategoryId(searchEntity.getProductCategoryId());
            searchObj.setProductDesc(searchEntity.getProductDesc());
            searchObj.setProductId(searchEntity.getProductId());
            searchObj.setProductImage(searchEntity.getProductImage());
            searchObj.setProductName(searchEntity.getProductName());
            searchObj.setProductRating(searchEntity.getProductRating());
            searchObj.setSellCount(searchEntity.getSellCount());

            searchRepository.save(searchObj);
        }
    }
}
