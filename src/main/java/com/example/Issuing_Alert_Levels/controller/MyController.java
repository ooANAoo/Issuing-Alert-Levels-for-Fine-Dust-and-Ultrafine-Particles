package com.example.Issuing_Alert_Levels.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;

public class MyController {

    @Autowired
    private Environment env;

    @GetMapping("/port")
    public String getServerPort() {
        return "Server is running on port: " + env.getProperty("local.server.port");
    }
}
