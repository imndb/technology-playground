package com.imndb.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class DemoController {

    @GetMapping("/time")
    public String time() {
        return "current time:" + LocalDateTime.now();
    }
}
