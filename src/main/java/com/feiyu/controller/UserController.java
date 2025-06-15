package com.feiyu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feiyu.entity.User;
import com.feiyu.param.UserQueryParam;
import com.feiyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Page<User> getUserList(UserQueryParam param) {
        log.info("查询用户列表，参数：{}", param);
        Page<User> page = userService.getUserPage(param);
        log.info("查询结果：{}", page);
        return page;
    }

    @PostMapping("/add")
    public boolean addUser(@RequestBody User user) {
        log.info("添加用户，参数：{}", user);
        return userService.save(user);
    }

    @PutMapping("/update")
    public boolean updateUser(@RequestBody User user) {
        log.info("更新用户，参数：{}", user);
        return userService.updateById(user);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        log.info("删除用户，ID：{}", id);
        return userService.removeById(id);
    }
} 