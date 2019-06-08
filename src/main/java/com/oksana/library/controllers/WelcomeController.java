package com.oksana.library.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping("/api")
    public String get (){
        return "Welcome to library !";
    }
}
