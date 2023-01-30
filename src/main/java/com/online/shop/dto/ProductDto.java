package com.online.shop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {

    private String name;
    private String category;
    private String price;
    private String description;
    private String image;
    private String id;
    private String quantity;


}
