package com.feiyu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feiyu.common.Result;
import com.feiyu.entity.Role;
import com.feiyu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public Result<Page<Role>> getRolePage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.success(roleService.page(new Page<>(pageNum, pageSize)));
    }

    @PostMapping("/add")
    public Result<Void> addRole(@RequestBody Role role) {
        roleService.save(role);
        return Result.success();
    }

    @PutMapping("/update")
    public Result<Void> updateRole(@RequestBody Role role) {
        roleService.updateById(role);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result<Void> deleteRole(@PathVariable Long id) {
        roleService.removeById(id);
        return Result.success();
    }
} 