package com.online.shop.controller;

import com.online.shop.dto.ProductDto;
import com.online.shop.service.ProductService;
import com.online.shop.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductValidator productValidator;

    @GetMapping("/addProduct")
    public String addProductPageGet(Model model) {
        //se va excuta "bussines logic"
        //dupa care intoarcem un nume de pagina
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String addProductPagePost(@ModelAttribute ProductDto productDto, BindingResult bindingResult,
                                     @RequestParam("productImage") MultipartFile multipartFile) throws IOException {
        System.out.println(multipartFile.getBytes());
        productValidator.validate(productDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "addProduct";
        }
        productService.addProduct(productDto, multipartFile);
        return "redirect:/addProduct";
    }
    @GetMapping("/home")
    public String homepageGet(Model model) {
        List<ProductDto> productDtoList = productService.getAllProductDtos();
        model.addAttribute("productDtoList", productDtoList);
        System.out.println(productDtoList);
        return "homepage";
    }

    @GetMapping("/product/{productId}")
    public String viewProductGet(@PathVariable(value ="productId") String productId){
        System.out.println("Am dat click pe produsul cu id-ul" + productId);
        return "viewProduct";
    }


}
