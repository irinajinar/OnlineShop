package com.online.shop.mapper;

import com.online.shop.dto.ChosenProductDto;
import com.online.shop.dto.ProductDto;
import com.online.shop.entities.ChosenProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChosenProductMapper {

    @Autowired
    private ProductMapper productMapper;

    public ChosenProductDto map(ChosenProduct chosenProduct){
        ChosenProductDto chosenProductDto= new ChosenProductDto();
        chosenProductDto.setQuantity(String.valueOf(chosenProduct.getChosenQuantity()));
        ProductDto productDto = productMapper.map(chosenProduct.getProduct());
        chosenProductDto.setProductDto(productDto);
        return chosenProductDto;
    }
}
