package com.feiyu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feiyu.common.Result;
import com.feiyu.entity.User;
import com.feiyu.param.LoginParam;
import com.feiyu.param.UserQueryParam;
import com.feiyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Result<Page<User>> getUserList(UserQueryParam param) {
        log.info("查询用户列表，参数：{}", param);
        if (param.getPageNum() == null) {
            param.setPageNum(1);
        }
        if (param.getPageSize() == null) {
            param.setPageSize(10);
        }
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

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody LoginParam loginParam) {
        log.info("登录请求参数：{}", loginParam);
        try {
            String token = userService.login(loginParam);
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            log.info("登录成功，返回token：{}", token);
            return Result.success(data);
        } catch (Exception e) {
            log.error("登录失败", e);
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/info")
    public Result<Map<String, Object>> getUserInfo() {
        Map<String, Object> userInfo = userService.getUserInfo();
        return Result.success(userInfo);
    }

    @PostMapping("/logout")
    public Result<Void> logout() {
        userService.logout();
        return Result.success(null);
    }

    @PostMapping("/reset-admin-password")
    public Result resetAdminPassword() {
        userService.resetAdminPassword();
        return Result.success();
    }
} 