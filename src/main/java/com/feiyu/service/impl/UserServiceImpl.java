package com.feiyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feiyu.entity.User;
import com.feiyu.mapper.UserMapper;
import com.feiyu.param.UserQueryParam;
import com.feiyu.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Page<User> getUserPage(UserQueryParam param) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        
        // 添加查询条件
        if (StringUtils.hasText(param.getUsername())) {
            wrapper.like(User::getUsername, param.getUsername());
        }
        
        // 按创建时间降序排序
        wrapper.orderByDesc(User::getCreateTime);
        
        // 执行分页查询
        return this.page(new Page<>(param.getPageNum(), param.getPageSize()), wrapper);
    }
} 