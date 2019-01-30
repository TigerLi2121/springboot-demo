package com.mm.swagger.controller;

import com.mm.swagger.common.R;
import com.mm.swagger.entity.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Api(tags = "用户管理")
@Slf4j
@RestController
public class UserController {

    ConcurrentHashMap<Long, UserInfo> map = new ConcurrentHashMap<>();

    @ApiOperation("新增用户")
    @PostMapping("/users")
    public R add(@RequestBody @Validated(UserInfo.Default.class) UserInfo userInfo) {
        log.info("add userInfo:{}", userInfo);
        map.put(userInfo.getId(), userInfo);
        map.values().forEach(e -> log.info("add map val:{}", e));
        return R.ok();
    }

    @ApiOperation("修改用户")
    @PutMapping("/users")
    public R updateById(@RequestBody @Validated({UserInfo.Default.class, UserInfo.Update.class}) UserInfo userInfo) {
        log.info("updateById userInfo:{}", userInfo);
        map.put(userInfo.getId(), userInfo);
        map.values().forEach(e -> log.info("updateById map val:{}", e));
        return R.ok();
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/users/{id}")
    public R deleteById(@PathVariable Long id) {
        log.info("deleteById id:{}", id);
        map.remove(id);
        return R.ok();
    }

    @ApiOperation("用户详情")
    @GetMapping("/users/{id}")
    public R findById(@PathVariable Long id) {
        log.info("findById id:{}", id);
        if (map.containsKey(id)) {
            return R.ok().put("user", map.get(id));
        } else {
            return R.error("用户不存在");
        }
    }

    @ApiOperation("用户列表")
    @GetMapping("/users")
    public R list(@ApiParam("查看第几页") @RequestParam int pageIndex,
                       @ApiParam("每页多少条") @RequestParam int pageSize) {
        log.info("list pageIndex:{}, pageIndex:{}", pageIndex, pageSize);
        List<UserInfo> list = new ArrayList<>(map.values());
        return R.ok().put("list", list);
    }

}
