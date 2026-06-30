package com.masaalbeeahforcontracting.accountingwherehousesystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {


    @GetMapping("/home")
    public String getHomePage(){

        return "home";
    }
}

