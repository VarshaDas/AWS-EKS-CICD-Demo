package com.varshadas.awseksspringboot.controllers;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@Slf4j
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);
    @GetMapping("/greeting")
    public String greeting()
    {

        logger.info("starting my greeting method.....");

        return "Greetings from Spring Boot with AWS EKS!";


    }
}

