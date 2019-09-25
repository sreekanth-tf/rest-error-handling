package com.tf.errorhandling.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @GetMapping
    public String home() {
        return "Error Handlers";
    }
}
