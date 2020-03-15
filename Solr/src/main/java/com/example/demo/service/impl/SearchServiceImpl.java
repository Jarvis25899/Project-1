package com.example.demo.service.impl;

import com.example.demo.entity.SearchEntity;
import com.example.demo.repository.SearchRepository;
import com.example.demo.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SolrTemplate solrTemplate;
    @Autowired
    private SearchRepository searchRepository;

    @Override
    public SearchEntity save(SearchEntity searchEntity) {
        return searchRepository.save(searchEntity);
   }
//
//    @Override
//    public List<SearchEntity> findByName(String productName) {
//        //String name = productName.toLowerCase();
////        Query query = new SimpleQuery(new SimpleStringCriteria("*:*"));
////        FilterQuery fq = new SimpleFilterQuery(new Criteria("autoCompleteData").contains(productName));
////        query.addFilterQuery(fq);
////        Object obj = solrTemplate.query("search", query, List.class);
//        return searchRepository.findByProductName(productName);
//    }
//
//    @Override
//    public List<SearchEntity> findByProductDesc(String productDesc) {
//
//        return searchRepository.findByProductDesc(productDesc);
//    }
//
//    @Override
//    public Iterable<SearchEntity> findAll() {
//        return searchRepository.findAll();
//    }


    @Override
    public List<SearchEntity> findByQueryAnnotation(String productName) {
//        SimpleQuery criteria = new SimpleQuery(new Criteria("autoCompleteData").contains(productName));
//        List<SearchEntity> searchEntity = solrTemplate.query("search", criteria ,SearchEntity.class);

        //return searchEntity;
        return searchRepository.findByQueryAnnotation(productName);
    }

    @Override
    public List<SearchEntity> findAll(Sort sort) {
        return searchRepository.findAll(Sort.by(Sort.Direction.DESC , "productRating"));
    }

    @Override
    public SearchEntity findByProductId(String productId) {
        return searchRepository.findByProductId(productId);
    }

    @Override
    public List<SearchEntity> findByProductCategoryId(String name, Sort sort) {
        return searchRepository.findByProductCategoryId(name,Sort.by(Sort.Direction.DESC,"productRating"));
    }

    @Override
    public List<SearchEntity> findByQuery(double price, Sort sort) {
        return searchRepository.findByQuery(price,Sort.by(Sort.Direction.DESC,"productRating"));
    }

    @Override
    public List<SearchEntity> findByQuery2(String productCategoryId , double price1, double price2, Sort sort) {
        return searchRepository.findByQuery2(productCategoryId,price1,price2,Sort.by(Sort.Direction.DESC,"price"));
    }

    @Override
    public List<SearchEntity> findByQuery2(String productCategoryId, Sort sort) {
        return searchRepository.findByQuery2(productCategoryId,Sort.by(Sort.Direction.DESC,"productRating"));
    }

    @Override
    public List<SearchEntity> findByQuery2(double price1, double price2, Sort sort) {
        return searchRepository.findByQuery2(price1,price2,Sort.by(Sort.Direction.DESC,"price"));
    }

    @Override
    public List<SearchEntity> findByQuery2(String search, String productCategoryId, double price1, double price2) {
        return searchRepository.findByQuery2(search,productCategoryId,price1,price2);
    }

    @Override
    public List<SearchEntity> findByQuery2(String search, String productCategoryId, Sort sort) {
        return searchRepository.findByQuery2(search,productCategoryId,Sort.by(Sort.Direction.DESC,"productRating"));
    }

    @Override
    public List<SearchEntity> findByQuery3(String search, double price1, double price2, Sort sort) {
        return searchRepository.findByQuery3(search , price1 , price2 , Sort.by(Sort.Direction.DESC,"price"));
    }

    @Override
    public List<SearchEntity> findByQuery4(String search, Sort sort) {
        return searchRepository.findByQuery4(search,Sort.by(Sort.Direction.DESC,"productRating"));
    }
}
