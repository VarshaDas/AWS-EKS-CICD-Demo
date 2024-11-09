package com.varshadas.awseksspringboot.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/greeting")
    public String greeting() {
        return "Greetings from Spring Boot with AWS EKS!";
    }
}

