package com.online.shop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class ShoppingCartDto {

    private List<ShoppingCartItemDto> items;

    public ShoppingCartDto(){
        items = new ArrayList<>();
    }

    public void add(ShoppingCartItemDto shoppingCartItemDTO){
        items.add(shoppingCartItemDTO);
    }
}
