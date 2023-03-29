package com.mm.controller;

import com.mm.common.util.R;
import com.mm.entity.User;
import com.mm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户信息
 *
 * @author lwl
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public R<R.Page> page(Map<String, Object> param) {

        return R.ok();
    }

    @PostMapping
    public R add(@RequestBody User user) {
        return R.ok();
    }

    @PutMapping
    public R update(@RequestBody User user) {
        return R.ok();
    }

    @DeleteMapping
    public R delete(List<Long> ids) {
        return R.ok();
    }
}
