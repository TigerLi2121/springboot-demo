package com.mm.api.web;

import com.mm.api.common.R;
import com.mm.api.entity.User;
import com.mm.api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 用户列表
 *
 * @author shmily
 */
@Api(value = "用户controller", tags = {"用户信息操作接口"})
@Validated
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户列表", notes = "获取全部用户信息")
    @GetMapping("/list")
    public R list() {
        return R.ok().put("list", userService.list());
    }

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "User")
    @PostMapping
    public R add(@Valid @RequestBody User user) {
        userService.add(user);
        return R.ok();
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "query")
    @GetMapping
    public R get(@NotNull(message = "id is null") Long id) {
        return R.ok().put("user", userService.get(id));
    }

    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "User")
    @PutMapping
    public R update(@Valid @RequestBody User user) {
        userService.update(user);
        return R.ok();
    }

    @ApiOperation(value = "删除用户", notes = "根据id删除")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("/{id}")
    public R del(@PathVariable Long id) {
        userService.del(id);
        return R.ok();
    }
}
