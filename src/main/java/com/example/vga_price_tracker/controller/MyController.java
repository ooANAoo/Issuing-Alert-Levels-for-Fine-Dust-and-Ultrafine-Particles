package com.example.vga_price_tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class MyController {

    @Autowired
    private Environment env;

    @GetMapping("/port")
    public String getServerPort() {
        return "Server is running on port: " + env.getProperty("local.server.port");
    }
}
