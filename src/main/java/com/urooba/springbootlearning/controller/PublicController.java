package com.urooba.springbootlearning.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

    @GetMapping("/welcome")
    public String welcomePage() {
        return "Welcome to the Open Eye Platform";
    }

    @GetMapping("/websiteFeatures")
    public String websiteFeatures() {
        return "It has nice features";
    }

    @GetMapping("/ContactUs")
    public String contactUs() {
        return "blogs-spring-boot@gmail.com";
    }
}
