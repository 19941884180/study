package com.mdy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/admin/hello")
    public String adminHello() {
        return "admin,hello";
    }

    @GetMapping("/user/hello")
    public String userHello() {
        return "user,hello";
    }
}
