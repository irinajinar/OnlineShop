package com.online.shop.service;


import com.online.shop.dto.ProductDto;
import com.online.shop.entities.Product;
import com.online.shop.mapper.ProductMapper;
import com.online.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    public void addProduct(ProductDto productDto) {
        Product product = productMapper.map(productDto);
        productRepository.save(product);
    }

    public List<ProductDto> getAllProductDtos() {
        List<Product> productList = productRepository.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : productList) {
            ProductDto productDto = productMapper.map(product);
            productDtoList.add(productDto);
        }

        return productDtoList;
    }
}
