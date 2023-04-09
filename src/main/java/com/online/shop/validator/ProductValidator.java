package com.online.shop.validator;

import com.online.shop.dto.ProductDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ProductValidator {
    public void validate(ProductDto productDto, BindingResult bindingResult) {
        try {
            Double price = Double.valueOf(productDto.getPrice());
            if (price <= 0) {
                FieldError fieldError = new FieldError("productDto", "price",
                        "Product price must be positive!");
                bindingResult.addError(fieldError);
            }
        } catch (NumberFormatException exception) {
            FieldError fieldError = new FieldError("productDto", "price",
                    "Product price is not a number!");
            bindingResult.addError(fieldError);
        }

    }
}
