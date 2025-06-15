package com.feiyu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.feiyu.entity.User;
import com.feiyu.param.UserQueryParam;

import java.util.List;

public interface UserService extends IService<User> {
    Page<User> getUserPage(UserQueryParam param);
    List<User> getSalesStaff();
} 