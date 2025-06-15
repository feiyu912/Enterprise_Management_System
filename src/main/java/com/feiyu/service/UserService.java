package com.feiyu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.feiyu.entity.User;
import com.feiyu.param.UserQueryParam;

public interface UserService extends IService<User> {
    Page<User> getUserPage(UserQueryParam param);
} 