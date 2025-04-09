package com.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DataController {
    @GetMapping("/test")
    public String testConnection() {
        return "{\"message\": \"Connection successful! Hello from Spring Boot!\"}";
    }

    @GetMapping("/data")
    public String getData() {
        return "{\"message\": \"Hello from Spring Boot!\"}"; // Return JSON
    }

    @PostMapping("/data")
    public String postData(@RequestBody String data) {
        return "{\"message\": \"Received: " + data + "\"}"; // Return JSON
    }
}