package com.mdy.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/webscoket/name")
    public String test(){
        return "ghfk";
    }
}
