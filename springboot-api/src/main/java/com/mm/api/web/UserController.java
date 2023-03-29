package com.mm.api.web;

import com.mm.api.entity.User;
import com.mm.common.util.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 用户列表
 *
 * @author shmily
 */
@Tag(name = "用户")
@RestController
@RequestMapping(value = "/user")
public class UserController {


    private final static ConcurrentHashMap<Long, User> users = new ConcurrentHashMap<>();

    @Operation(summary = "用户列表")
    @GetMapping
    public R<R.Page<List<User>>> page() {
        return R.ok(users.values().stream().collect(Collectors.toList()), users.size());
    }

    @Operation(summary = "新增用户")
    @PostMapping
    public R add(@RequestBody User user) {
        users.put(user.getId(), user);
        return R.ok();
    }

    @Operation(summary = "获取用户")
    @GetMapping("/{id}")
    public R get(@NotNull(message = "id is null") @PathVariable Long id) {
        return R.ok(users.get(id));
    }

    @Operation(summary = "修改用户")
    @PutMapping
    public R update(@RequestBody User user) {
        users.put(user.getId(), user);
        return R.ok();
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/{id}")
    public R del(@PathVariable Long id) {
        users.remove(id);
        return R.ok();
    }
}
