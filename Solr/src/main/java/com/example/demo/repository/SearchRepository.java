package com.example.demo.repository;

import com.example.demo.entity.SearchEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends SolrCrudRepository<SearchEntity, String> {

//    @Query(value = "*:*", filters = "autoCompleteData:\"ip\"")
SearchEntity findByProductId(String productId);
//
//    List<SearchEntity> findByProductDesc(String productDesc);

   // @Query("autoCompleteData:*?0*")
   //@Query("(productName:*?0*)^8 OR (autoCompleteData:*?0*)")
   //@Query("autoCompleteData:*?0*")
   //@Query("autoCompleteData:*?0* OR (productName:*?0*)^5")
   @Query("(productName:?0)^8 OR (autoCompleteData:*?0*)")
   List<SearchEntity> findByQueryAnnotation(String name);

    @Query("autoCompleteData:*:*")
    List<SearchEntity> findAll(Sort sort);

    List<SearchEntity> findByProductCategoryId(String name,Sort sort);

    @Query("price:[* TO ?0]")
    List<SearchEntity> findByQuery(double price,Sort sort);

    @Query("(productCategoryId:?0) AND (price:[?1 TO ?2])")
    List<SearchEntity> findByQuery2(String productCategoryId,double price1 , double price2 , Sort sort);

    @Query("productCategoryId:?0")
    List<SearchEntity> findByQuery2(String productCategoryId,Sort sort);

    @Query("price:[?0 TO ?1]")
    List<SearchEntity> findByQuery2(double price1 , double price2 , Sort sort);

    @Query("(autoCompleteData:*?0*)^8 AND ((productCategoryId:?1) AND (price:[?2 TO ?3]))")
    List<SearchEntity> findByQuery2(String search,String productCategoryId,double price1 , double price2 );

    @Query("(autoCompleteData:*?0*)^8 AND (productCategoryId:?1)")
    List<SearchEntity> findByQuery2(String search,String productCategoryId,Sort sort);

    @Query("(autoCompleteData:*?0*)^8 AND (price:[?1 TO ?2])^2")
    List<SearchEntity> findByQuery3(String search, double price1 , double price2 , Sort sort);

    @Query("autoCompleteData:*?0* OR (productName:*?0*)^5")
    List<SearchEntity> findByQuery4(String search , Sort sort);


}
