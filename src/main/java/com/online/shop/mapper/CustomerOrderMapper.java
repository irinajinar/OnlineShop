package com.online.shop.mapper;

import com.online.shop.dto.ChosenProductDto;
import com.online.shop.dto.CustomerOrderDto;
import com.online.shop.entities.ChosenProduct;
import com.online.shop.entities.CustomerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerOrderMapper {

    @Autowired
    private ChosenProductMapper chosenProductMapper;

    public CustomerOrderDto map(CustomerOrder customerOrder) {
        CustomerOrderDto customerOrderDto = new CustomerOrderDto();
        customerOrderDto.setCustomerName(customerOrder.getUser().getFullName());
        List<ChosenProductDto> chosenProductDtoList = new ArrayList<>();
        for (ChosenProduct chosenProduct : customerOrder.getChosenProducts()) {
            ChosenProductDto chosenProductDto = chosenProductMapper.map(chosenProduct);
            chosenProductDtoList.add(chosenProductDto);
        }
        customerOrderDto.setChosenProductDtoList(chosenProductDtoList);
        return customerOrderDto;
    }
}
