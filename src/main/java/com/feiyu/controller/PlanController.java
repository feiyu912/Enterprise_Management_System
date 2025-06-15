package com.feiyu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feiyu.common.Result;
import com.feiyu.entity.Plan;
import com.feiyu.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    private PlanService planService;

    @GetMapping("/list")
    public Result<Page<Plan>> getPlanList(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<Plan> page = planService.page(new Page<>(pageNum, pageSize));
        return Result.success(page);
    }

    @PostMapping("/add")
    public Result<Void> addPlan(@RequestBody Plan plan) {
        planService.save(plan);
        return Result.success();
    }

    @PutMapping("/update")
    public Result<Void> updatePlan(@RequestBody Plan plan) {
        planService.updateById(plan);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result<Void> deletePlan(@PathVariable Long id) {
        planService.removeById(id);
        return Result.success();
    }
} 