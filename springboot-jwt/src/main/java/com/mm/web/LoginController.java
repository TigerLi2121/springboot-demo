package com.mm.web;

import com.mm.common.jwt.JwtHelper;
import com.mm.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录
 *
 * @author lwl
 * @date 2019/4/10
 */
@Slf4j
@RestController
public class LoginController {

    @PostMapping("/login")
    public R login(String username, String password) {
        log.info("username:{}, password:{}", username, password);
        return R.ok().put("token", JwtHelper.getToken(123));
    }
}
