package com.selim.taskmanager.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/public/hello")
    public String publicHello() {
        return "Merhaba, bu endpoint herkese açık!";
    }

    @GetMapping("/api/private/hello")
    public String privateHello() {
        return "Selam admin! Bu endpoint sadece giriş yapanlara açık.";
    }
}
