package com.online.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("/addProduct")
    public String addProductPageGet(){
        //se va excuta "bussines logic"
        //dupa care intoarcem un nume de pagina
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String addProductPagePost(){
        System.out.println("S-a apelat metoda de tip POST pe addProduct!!!");
        return "addProduct";
    }
}
