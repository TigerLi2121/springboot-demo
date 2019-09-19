package com.mm.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * hello
 *
 * @author shmily
 * @date 2019/6/21
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class HelloOAuth2Controller {

    @GetMapping("/hello")
    public String hello(String name) {
        log.info("hello name:{}", name);
        return "Hello " + name;
    }
}
