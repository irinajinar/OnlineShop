package com.online.shop.controller;

import com.online.shop.dto.ProductDto;
import com.online.shop.dto.UserDto;
import com.online.shop.service.ProductService;
import com.online.shop.service.UserService;
import com.online.shop.validator.ProductValidator;
import com.online.shop.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductValidator productValidator;

    @Autowired
    private UserValidator userValidator;

      @GetMapping("/addProduct")
    public String addProductPageGet(Model model) {
        //se va excuta "business logic"
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
    public String viewProductGet(@PathVariable(value = "productId") String productId, Model model) {
        Optional<ProductDto> optionalProductDto = productService.getOneProductDtoById(productId);
        if (optionalProductDto.isEmpty()) {
            return "error";
        }
        ProductDto productDto = optionalProductDto.get();
        model.addAttribute("productDtoXX", productDto);
        System.out.println("Am dat click pe produsul cu id-ul" + productId);
        return "viewProduct";
    }

    @GetMapping("/register")
    public String registerPageGet(Model model){
        UserDto userDto=new UserDto();
        model.addAttribute("userDto", userDto);



        return "register";
    }

    @PostMapping("register")
    public String registerPagePost(@ModelAttribute UserDto userDto, BindingResult bindingResult) throws IOException {


        System.out.println(userDto);
        userValidator.validate(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.register(userDto);
        return "redirect:/register";
    }


}
