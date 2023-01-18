package com.online.shop.mapper;


import com.online.shop.dto.ProductDto;
import com.online.shop.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product map(ProductDto productDto){
        Product product= new Product();
        product.setName(productDto.getName());
        product.setCategory(productDto.getCategory());
        product.setPrice(Double.parseDouble(productDto.getPrice()));
        product.setDescription(productDto.getDescription());
        return product;
    }

    public ProductDto map(Product product) {
        ProductDto productDto= new ProductDto();
        productDto.setName(product.getName());
        productDto.setCategory(product.getCategory());
        productDto.setPrice(String.valueOf(product.getPrice()));
        productDto.setDescription(product.getDescription());
        return productDto;
    }
}
