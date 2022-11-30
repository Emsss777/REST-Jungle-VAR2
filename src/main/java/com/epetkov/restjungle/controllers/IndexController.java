package com.epetkov.restjungle.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class IndexController {

    @RequestMapping("/")
    public String index() {

        return "Welcome to the Jungle!";
    }
}
