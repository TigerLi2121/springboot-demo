package com.mm.api.web;

import com.mm.api.common.R;
import com.mm.api.entity.User;
import com.mm.api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "用户controller", description = "用户信息操作", tags = {"用户信息操作接口"})
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户列表", notes = "获取全部用户信息")
    @GetMapping("getAll")
    public R getUserList() {
        return R.ok().put("list", userService.getUserList());
    }

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @PostMapping("add")
    public R addUser(@RequestBody User user) throws Exception {
        userService.addUser(user);
        return R.ok();
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping("get/{id}")
    public R getUser(@PathVariable Long id) {
        return R.ok().put("user",userService.getUser(id));
    }

    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @PostMapping("update/{id}")
    public R updateUser(@PathVariable Long id, @RequestBody User user) {
        userService.updateUser(id, user);
        return R.ok();
    }

    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("delete/{id}")
    public R deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return R.ok();
    }
}
