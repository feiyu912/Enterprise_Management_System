package com.feiyu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.feiyu.entity.Region;
import com.feiyu.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping("/provinces")
    public List<Map<String, Object>> getProvinces() {
        List<Region> provinces = regionService.list(new LambdaQueryWrapper<Region>()
                .eq(Region::getLevel, 1));
        
        return provinces.stream().map(province -> {
            Map<String, Object> map = Map.of(
                "value", province.getCode(),
                "label", province.getName()
            );
            return map;
        }).collect(Collectors.toList());
    }

    @GetMapping("/children/{parentCode}")
    public List<Map<String, Object>> getChildren(@PathVariable String parentCode) {
        Region parent = regionService.getOne(new LambdaQueryWrapper<Region>()
                .eq(Region::getCode, parentCode));
        
        if (parent == null) {
            return new ArrayList<>();
        }

        List<Region> children = regionService.list(new LambdaQueryWrapper<Region>()
                .eq(Region::getParentId, parent.getId()));
        
        return children.stream().map(child -> {
            Map<String, Object> map = Map.of(
                "value", child.getCode(),
                "label", child.getName(),
                "leaf", child.getLevel() >= 3
            );
            return map;
        }).collect(Collectors.toList());
    }
} 