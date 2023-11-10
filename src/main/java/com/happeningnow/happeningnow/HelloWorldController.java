package com.happeningnow.happeningnow;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @RequestMapping("/")
    public String Application() {
        return "Hello World from Spring Boot!!!!";
    }
}
