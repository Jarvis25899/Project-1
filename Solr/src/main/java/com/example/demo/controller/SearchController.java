package com.example.demo.controller;

import com.example.demo.dto.FilterDTO;
import com.example.demo.dto.SearchDTO;
import com.example.demo.dto.SearchString;
import com.example.demo.entity.SearchEntity;
import com.example.demo.service.SearchService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solrsearch")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class SearchController {
    @Autowired
    SearchService searchService;



    //List<SearchDTO> list = new ArrayList<>() ;
    //int randomVariable = 500;
 @PostMapping("/save")
SearchEntity save(@RequestBody SearchEntity searchEntity){

        return searchService.save(searchEntity);
    }

//    @GetMapping("/get/{name}")
//    List<SearchEntity> findByName(@PathVariable("name") String productName){
//        //productName.toLowerCase();
//
//        searchService.findByName(productName);
//        //list.addAll(searchService.findByProductDesc(productName));
//
//
//        return list;
//
//    }
//
//    @GetMapping("/popular")
//    Iterable<SearchEntity> findAll(){
//        return searchService.findAll();
//    }


    @PostMapping("/search")
    List<SearchEntity> find(@RequestBody SearchString searchString){
        SearchDTO searchDTO = new SearchDTO();
        SearchEntity searchEntity = new SearchEntity();
        BeanUtils.copyProperties(searchDTO,searchEntity);
        return searchService.findByQueryAnnotation(searchString.getSearchString());
    }

//    @GetMapping("/popular/{name}")
//    List<SearchEntity> findByString(@PathVariable("name") String name){
//        return searchService.findByString(name,Sort.by(Sort.Direction.DESC , "productRating"));
//    }

    @GetMapping("/popular")
    List<SearchEntity> findByString(){
        return searchService.findAll(Sort.by(Sort.Direction.DESC , "productRating"));
    }

//    @PostMapping("/get")
//    void findByProductId(@RequestBody SearchString searchString){
//      SearchEntity searchEntity = searchService.findByProductId(searchString.getSearchString());
//
//        //System.out.println(searchEntity.getPrice());
//
//        searchEntity.setQuantity(searchEntity.getQuantity()+randomVairable);
//        searchService.save(searchEntity);
//    }

    @GetMapping("/category/{name}")
    List<SearchEntity> findByProductCategoryId(@PathVariable("name") String name) {
     return searchService.findByProductCategoryId(name,Sort.by(Sort.Direction.DESC,"productRating"));
    }

    @GetMapping("/filter/{price}")
    List<SearchEntity> findByQuery(@PathVariable("price") double price){
     return searchService.findByQuery(price,Sort.by(Sort.Direction.DESC,"productRating"));
    }

    @PostMapping("/filter2")
    List<SearchEntity> findByQuery2(@RequestBody FilterDTO filterDTO) {

        if(filterDTO.getSearchString()==null && filterDTO.getName()==null && filterDTO.getMinimumPrice()==0 && filterDTO.getMaximumPrice()==0){
            return searchService.findAll(Sort.by(Sort.Direction.DESC,"productRating"));
        }
        else if(filterDTO.getSearchString()!=null && filterDTO.getName()==null && filterDTO.getMinimumPrice()==0 && filterDTO.getMaximumPrice()==0){
            return searchService.findByQuery4(filterDTO.getSearchString(),Sort.by(Sort.Direction.DESC,"productRating"));
        }
        else if(filterDTO.getSearchString()==null && filterDTO.getName()!=null && filterDTO.getMinimumPrice()==0 && filterDTO.getMaximumPrice()==0) {
            return searchService.findByQuery2(filterDTO.getName(), Sort.by(Sort.Direction.DESC, "productRating"));
        }
        else if(filterDTO.getSearchString()!=null && filterDTO.getName()!=null && filterDTO.getMinimumPrice()==0 && filterDTO.getMaximumPrice()==0){
            return searchService.findByQuery2(filterDTO.getSearchString(),filterDTO.getName(),Sort.by(Sort.Direction.DESC,"productRating"));
        }
        else if(filterDTO.getSearchString()==null && filterDTO.getName()==null && filterDTO.getMinimumPrice()!=0 && filterDTO.getMaximumPrice()!=0){
            return searchService.findByQuery2(filterDTO.getMinimumPrice(),filterDTO.getMaximumPrice(),Sort.by(Sort.Direction.DESC,"price"));
        }
        else if(filterDTO.getSearchString()!=null && filterDTO.getName()==null && filterDTO.getMinimumPrice()!=0 && filterDTO.getMaximumPrice()!=0){
            return searchService.findByQuery3(filterDTO.getSearchString(),filterDTO.getMinimumPrice(),filterDTO.getMaximumPrice(),Sort.by(Sort.Direction.DESC,"price"));
        }
        else if(filterDTO.getSearchString()==null && filterDTO.getName()!=null && filterDTO.getMinimumPrice()!=0 && filterDTO.getMaximumPrice()!=0){
            return searchService.findByQuery2(filterDTO.getName(),filterDTO.getMinimumPrice(),filterDTO.getMaximumPrice(),Sort.by(Sort.Direction.DESC,"price"));
        }
        return searchService.findByQuery2(filterDTO.getSearchString(),filterDTO.getName(),filterDTO.getMinimumPrice(),filterDTO.getMaximumPrice());
    }

    @GetMapping("/filter3/{name}")
    List<SearchEntity> findByQuery4(@PathVariable("name") String name){
     return searchService.findByQuery4(name,Sort.by(Sort.Direction.DESC,"productRating"));
    }

}
