package com.feiyu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feiyu.entity.Dept;
import java.util.List;
import java.util.Map;

public interface DeptService extends IService<Dept> {
    List<Map<String, Object>> getDeptTree();
    void deleteDept(Long id);
} 