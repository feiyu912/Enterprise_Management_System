package com.feiyu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feiyu.common.Result;
import com.feiyu.entity.User;
import com.feiyu.param.UserQueryParam;
import com.feiyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Result<Page<User>> getUserList(UserQueryParam param) {
        log.info("查询用户列表，参数：{}", param);
        Page<User> page = userService.getUserPage(param);
        log.info("查询结果：{}", page);
        return Result.success(page);
    }

    @PostMapping("/add")
    public Result<Boolean> addUser(@RequestBody User user) {
        log.info("添加用户，参数：{}", user);
        return Result.success(userService.save(user));
    }

    @PutMapping("/update")
    public Result<Boolean> updateUser(@RequestBody User user) {
        log.info("更新用户，参数：{}", user);
        return Result.success(userService.updateById(user));
    }

    @DeleteMapping("/delete/{id}")
    public Result<Boolean> deleteUser(@PathVariable Long id) {
        log.info("删除用户，ID：{}", id);
        return Result.success(userService.removeById(id));
    }

    @GetMapping("/sales")
    public Result<List<User>> getSalesStaff() {
        List<User> salesStaff = userService.getSalesStaff();
        return Result.success(salesStaff);
    }
} 