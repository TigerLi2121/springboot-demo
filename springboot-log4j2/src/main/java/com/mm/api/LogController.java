package com.mm.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志
 *
 * @author lwl
 * @date 2019/5/11
 */
@Slf4j
@RestController
public class LogController {

    @GetMapping("/log")
    public void log() {
        log.debug("This is debug");
        log.info("This is info");
        log.warn("This is warn");
        log.error("This is error");
    }
}
