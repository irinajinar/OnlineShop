package com.online.shop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CustomerOrderDto {
    private String customerName;
    private List<ChosenProductDto> chosenProductDtoList;
}