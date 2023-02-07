package com.online.shop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ShoppingCartItemDto {

    @ToString.Exclude
    private String image;
    private String name;
    private String price;
    private String quantity;
    private String total;

}