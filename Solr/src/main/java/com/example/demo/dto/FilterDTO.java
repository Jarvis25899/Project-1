package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterDTO {

    private String searchString;
    private String name;
    private double minimumPrice = 0;
    private double maximumPrice = 0;

}
