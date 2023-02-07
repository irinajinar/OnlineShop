package com.online.shop.controller;

import com.online.shop.dto.ChosenProductDto;
import com.online.shop.dto.ProductDto;
import com.online.shop.dto.ShoppingCartDto;
import com.online.shop.dto.UserDto;
import com.online.shop.service.ProductService;
import com.online.shop.service.ShoppingCartService;
import com.online.shop.service.UserService;
import com.online.shop.validator.ProductValidator;
import com.online.shop.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
    private ShoppingCartService shoppingCartService;

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
        ChosenProductDto chosenProductDto= new ChosenProductDto();
        model.addAttribute("chosenProductDto", chosenProductDto);
        return "viewProduct";
    }

    @GetMapping("/register")
    public String registerPageGet(Model model,@RequestParam(value = "userAddedSuccessfully", required = false) Boolean userAddedSuccessfully){
        System.out.println(userAddedSuccessfully);
        UserDto userDto=new UserDto();
        model.addAttribute("userDto", userDto);
        if (userAddedSuccessfully != null && userAddedSuccessfully) {
            model.addAttribute("message", "User was added successfuly!");
        }
        return "register";
    }

    @PostMapping("/product/{productId}")
    public String viewProductPost(@PathVariable(value = "productId") String productId, Model model,
                                  @ModelAttribute ChosenProductDto chosenProductDTO){
        String loggedInUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        shoppingCartService.addToCart(chosenProductDTO, productId, loggedInUserEmail);
        return "redirect:/product/"+productId;
    }


    @PostMapping("/register")
    public String registerPagePost(@ModelAttribute UserDto userDto, BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) throws IOException {
        System.out.println(userDto);
        userValidator.validate(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "register";
        }
        userService.register(userDto);
        redirectAttributes.addAttribute("userAddedSuccessfully", true);
        return "redirect:/register";
    }

    @GetMapping("/login")
    public String loginGet(){
          return "login";
    }

    @GetMapping("/cart")
    public String cartGet(Model model){
        String loggedInUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        ShoppingCartDto shoppingCartDTO = shoppingCartService.getShoppingCartDTOByUserEmail(loggedInUserEmail);
        model.addAttribute("shoppingCartDTO", shoppingCartDTO);
        System.out.println("ShoppingCartDTO este:" + shoppingCartDTO);
        return "cart";
    }


}
