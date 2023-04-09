package com.online.shop.controller;

import com.online.shop.dto.*;
import com.online.shop.service.CustomerOrderService;
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
    private UserService userService;

    @Autowired
    private ProductService productService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private CustomerOrderService customerOrderService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private ProductValidator productValidator;


    @GetMapping("/addProduct")
    public String addProductPageGet(Model model) {
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
    public String homePageGet(Model model) {
        List<ProductDto> productDtoList = productService.getAllProductDtos();
        model.addAttribute("productDtoList", productDtoList);
        System.out.println(productDtoList);
        return "homepage";

    }

    @GetMapping("/product/{productId}")
    public String viewProductGet(@PathVariable(value = "productId") String productId, Model model) {
        Optional<ProductDto> optionalProductDto = productService.getProductDtoById(productId);
        if (optionalProductDto.isEmpty()) {
            return "error";
        }
        ProductDto productDto = optionalProductDto.get();
        model.addAttribute("productDto", productDto);
        ChosenProductDto chosenProductDto = new ChosenProductDto();
        model.addAttribute("chosenProductDto", chosenProductDto);
        return "viewProduct";
    }

    @PostMapping("/product/{productId}")
    public String viewProductPost(@PathVariable(value = "productId") String productId, Model model,
                                  @ModelAttribute ChosenProductDto chosenProductDto) {
        String loggedInUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        shoppingCartService.addToCart(chosenProductDto, productId, loggedInUserEmail);
        return "redirect:/product/" + productId;
    }

    @GetMapping("/register")
    public String registerPageGet(Model model, @RequestParam(value = "userAddedSuccessfully", required = false) Boolean userAddedSuccessfully) {
        System.out.println(userAddedSuccessfully);
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        if (userAddedSuccessfully != null && userAddedSuccessfully) {
            model.addAttribute("message", "User was added successfully!");
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerPagePost(@ModelAttribute UserDto userDto, BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {

        System.out.println(userDto);
        userValidator.validate(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "register";
        }
        userService.registerUser(userDto);
        redirectAttributes.addAttribute("userAddedSuccessfully", true);
        return "redirect:/register";
    }

    @GetMapping("/login")
    public String loginGet() {
        return "login";
    }

    @GetMapping("/checkout")
    public String checkoutGet(Model model) {
        String loggedInUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        ShoppingCartDto shoppingCartDto = shoppingCartService.getShoppingCartDtoByUserEmail(loggedInUserEmail);
        model.addAttribute("shoppingCartDto", shoppingCartDto);

        UserDetailsDto userDetailsDto = userService.getUserDetailsDtoByEmail(loggedInUserEmail);
        model.addAttribute("userDetailsDto", userDetailsDto);

        return "checkout";
    }

    @GetMapping("/cart")
    public String cartGet(Model model) {
        String loggedInUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        ShoppingCartDto shoppingCartDto = shoppingCartService.getShoppingCartDtoByUserEmail(loggedInUserEmail);
        model.addAttribute("shoppingCartDto", shoppingCartDto);
        System.out.println("ShoppingcartDto este: " + shoppingCartDto);
        return "cart";
    }

    @PostMapping("/sendOrder")
    public String sendOrderPost(@ModelAttribute("userDetailsDto") UserDetailsDto userDetailsDto) {
        System.out.println(userDetailsDto);
        String loggedInUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        customerOrderService.addCustomerOrder(loggedInUserEmail, userDetailsDto.getShippingAddress());
        return "confirmation";
    }

    @GetMapping("/contact")
    public String contactGet(Model model) {
        return "contact";
    }

    @GetMapping("/ordersTracking")
    public String ordersTrackingGet(Model model) {
        List<CustomerOrderDto> customerOrderDtoList = customerOrderService.getAllCustomerOrderDtos();
        model.addAttribute("customerOrderDtoList", customerOrderDtoList);
        System.out.println(customerOrderDtoList);
        return "ordersTracking";
    }
}




