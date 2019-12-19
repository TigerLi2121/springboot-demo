package com.mm.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class IndexController {

    @GetMapping("/")
    public String index(String name) {
        log.debug("index:{}", name);
        return "Hello World!";
    }
}
