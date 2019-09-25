package com.tf.errorhandling.controller;

import com.tf.errorhandling.exceptions.PacException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @GetMapping("unhandled")
    public String unhandledError() {
        if (true)
            throw new RuntimeException("Some error");
        return "Error Handlers";
    }

    @GetMapping("pac")
    public String pacError() {
        if (true)
            throw new PacException("Pac Specific Error");
        return "Error Handlers";
    }
}
