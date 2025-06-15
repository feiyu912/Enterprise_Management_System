package com.feiyu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feiyu.common.Result;
import com.feiyu.entity.Opportunity;
import com.feiyu.service.OpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/opportunity")
public class OpportunityController {

    @Autowired
    private OpportunityService opportunityService;

    @GetMapping("/list")
    public Result<Page<Opportunity>> getOpportunityPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.success(opportunityService.page(new Page<>(pageNum, pageSize)));
    }

    @PostMapping("/add")
    public Result<Void> addOpportunity(@RequestBody Opportunity opportunity) {
        opportunityService.save(opportunity);
        return Result.success();
    }

    @PutMapping("/update")
    public Result<Void> updateOpportunity(@RequestBody Opportunity opportunity) {
        opportunityService.updateById(opportunity);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result<Void> deleteOpportunity(@PathVariable Long id) {
        opportunityService.removeById(id);
        return Result.success();
    }
} 