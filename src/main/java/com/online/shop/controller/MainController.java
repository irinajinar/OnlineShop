package com.online.shop.controller;

import com.online.shop.dto.ProductDto;
import com.online.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    private ProductService productService;

    @GetMapping("/addProduct")
    public String addProductPageGet(Model model){
        //se va excuta "bussines logic"
        //dupa care intoarcem un nume de pagina
        ProductDto productDto= new ProductDto();
        model.addAttribute("productDto", productDto);
        return "addProduct";
    }
    @PostMapping("/addProduct")
    public String addProductPagePost(@ModelAttribute ProductDto productDto){
        productService.addProduct(productDto);
        return "addProduct";
    }
}
