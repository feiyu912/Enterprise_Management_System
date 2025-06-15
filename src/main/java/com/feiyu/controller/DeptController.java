package com.feiyu.controller;

import com.feiyu.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/tree")
    public List<Map<String, Object>> getDeptTree() {
        return deptService.getDeptTree();
    }

    @DeleteMapping("/{id}")
    public void deleteDept(@PathVariable Long id) {
        deptService.deleteDept(id);
    }
} 