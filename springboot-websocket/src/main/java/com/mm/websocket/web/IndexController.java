package com.mm.websocket.web;

import com.mm.websocket.config.ClientConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/text")
    @ResponseBody
    public String text(String text) {
        ClientConfig.sendText(text);
        return "ok";
    }
}
