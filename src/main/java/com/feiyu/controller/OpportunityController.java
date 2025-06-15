package com.feiyu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feiyu.common.Result;
import com.feiyu.entity.Opportunity;
import com.feiyu.service.OpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) String stage) {
        
        LambdaQueryWrapper<Opportunity> wrapper = new LambdaQueryWrapper<>();
        
        // 添加查询条件
        if (StringUtils.hasText(title)) {
            wrapper.like(Opportunity::getTitle, title);
        }
        if (customerId != null) {
            wrapper.eq(Opportunity::getCustomerId, customerId);
        }
        if (StringUtils.hasText(stage)) {
            wrapper.eq(Opportunity::getStage, stage);
        }
        
        // 按创建时间倒序排序
        wrapper.orderByDesc(Opportunity::getCreateTime);
        
        return Result.success(opportunityService.page(new Page<>(pageNum, pageSize), wrapper));
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