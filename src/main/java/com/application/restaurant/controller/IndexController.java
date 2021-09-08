package com.application.restaurant.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class IndexController {
    // inject via application.properties
    @Value("${welcome.message:test}")
    private String message = "Hello World";
/*
    @RequestMapping("/index")
    public String welcome(Map<String, Object> model) {
        model.put("message", this.message);
        return "index";
    }
    */
}
