package com.online.shop.mapper;


import com.online.shop.dto.ProductDto;
import com.online.shop.entities.Product;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class ProductMapper {

    public Product map(ProductDto productDto, MultipartFile multipartFile){
        Product product= new Product();
        product.setName(productDto.getName());
        product.setCategory(productDto.getCategory());
        product.setPrice(Double.valueOf(productDto.getPrice()));
        product.setDescription(productDto.getDescription());
        product.setImage(convertToBytes(multipartFile));
        product.setQuantity(Integer.valueOf(productDto.getQuantity()));
        return product;
    }

    private byte[] convertToBytes(MultipartFile multipartFile) {
        try{
            return multipartFile.getBytes();
        }catch (IOException exception){
            return new byte[0];
        }
    }

    public ProductDto map(Product product) {
        ProductDto productDto= new ProductDto();
        productDto.setName(product.getName());
        productDto.setCategory(product.getCategory());
        productDto.setPrice(String.valueOf(product.getPrice()));
        productDto.setDescription(product.getDescription());
        productDto.setImage(Base64.encodeBase64String(product.getImage()));
        productDto.setId(String.valueOf(product.getId()));
        productDto.setQuantity(String.valueOf(product.getQuantity()));
        return productDto;
    }
}
