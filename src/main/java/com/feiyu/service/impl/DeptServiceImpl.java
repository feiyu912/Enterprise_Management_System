package com.feiyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feiyu.entity.Dept;
import com.feiyu.mapper.DeptMapper;
import com.feiyu.service.DeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    @Override
    public List<Map<String, Object>> getDeptTree() {
        // 查询所有部门
        List<Dept> deptList = this.list();
        
        // 构建树形结构
        List<Map<String, Object>> result = new ArrayList<>();
        Map<Long, List<Dept>> parentMap = deptList.stream()
                .collect(Collectors.groupingBy(Dept::getParentId));
        
        // 获取根节点
        List<Dept> rootDepts = parentMap.getOrDefault(0L, new ArrayList<>());
        
        // 递归构建树
        for (Dept dept : rootDepts) {
            Map<String, Object> node = new HashMap<>();
            node.put("id", dept.getId());
            node.put("label", dept.getName());
            node.put("children", buildChildren(dept.getId(), parentMap));
            result.add(node);
        }
        
        return result;
    }

    private List<Map<String, Object>> buildChildren(Long parentId, Map<Long, List<Dept>> parentMap) {
        List<Dept> children = parentMap.getOrDefault(parentId, new ArrayList<>());
        List<Map<String, Object>> result = new ArrayList<>();
        
        for (Dept dept : children) {
            Map<String, Object> node = new HashMap<>();
            node.put("id", dept.getId());
            node.put("label", dept.getName());
            node.put("children", buildChildren(dept.getId(), parentMap));
            result.add(node);
        }
        
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDept(Long id) {
        // 递归删除子部门
        List<Dept> children = this.list(new LambdaQueryWrapper<Dept>()
                .eq(Dept::getParentId, id));
        
        for (Dept child : children) {
            deleteDept(child.getId());
        }
        
        // 删除当前部门
        this.removeById(id);
    }
} 