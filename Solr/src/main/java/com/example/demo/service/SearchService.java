package com.example.demo.service;

import com.example.demo.entity.SearchEntity;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface SearchService {

    SearchEntity save(SearchEntity searchEntity);
//    List<SearchEntity> findByName(String productName);
//    List<SearchEntity> findByProductDesc(String productDesc);
//    Iterable<SearchEntity> findAll();
       SearchEntity findByProductId(String productId);

    List<SearchEntity> findByQueryAnnotation(String name);

    List<SearchEntity> findAll(Sort sort);

    List<SearchEntity> findByProductCategoryId(String name,Sort sort);

    List<SearchEntity> findByQuery(double price,Sort sort);

    List<SearchEntity> findByQuery2(String productCategoryId , double price1 , double price2 , Sort sort);

    List<SearchEntity> findByQuery2(String productCategoryId , Sort sort);

    List<SearchEntity> findByQuery2(double price1 , double price2 , Sort sort);

    List<SearchEntity> findByQuery2(String search,String productCategoryId,double price1 , double price2 );

    List<SearchEntity> findByQuery2(String search,String productCategoryId,Sort sort);

    List<SearchEntity> findByQuery3(String search, double price1 , double price2 , Sort sort);

    List<SearchEntity> findByQuery4(String search , Sort sort);
}
