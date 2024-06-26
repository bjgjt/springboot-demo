package com.lnt.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ping")
public class PingController {
    @GetMapping(path = "")
    public String ping() {
        return "Hello World!";
    }
}
