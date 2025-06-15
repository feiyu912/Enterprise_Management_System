package com.feiyu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.feiyu.entity.User;
import com.feiyu.param.UserQueryParam;
import com.feiyu.param.LoginParam;
import java.util.List;
import java.util.Map;

public interface UserService extends IService<User> {
    Page<User> getUserPage(UserQueryParam param);
    List<User> getSalesStaff();
    String login(LoginParam loginParam);
    Map<String, Object> getUserInfo();
    void logout();
    void resetAdminPassword();
} 