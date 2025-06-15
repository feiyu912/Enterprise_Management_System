package com.feiyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feiyu.entity.User;
import com.feiyu.mapper.UserMapper;
import com.feiyu.param.LoginParam;
import com.feiyu.param.UserQueryParam;
import com.feiyu.service.UserService;
import com.feiyu.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private JwtUtil jwtUtil;

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

    @Override
    public List<User> getSalesStaff() {
        return baseMapper.selectSalesStaff();
    }

    @Override
    public String login(LoginParam loginParam) {
        if (loginParam == null || loginParam.getUsername() == null || loginParam.getPassword() == null) {
            throw new RuntimeException("用户名和密码不能为空");
        }

        // 根据用户名查询用户
        User user = this.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, loginParam.getUsername()));
        
        if (user == null) {
            throw new RuntimeException("用户名或密码错误");
        }

        // 打印密码信息用于调试
        System.out.println("Input username: " + loginParam.getUsername());
        System.out.println("Input password: " + loginParam.getPassword());
        System.out.println("Stored password: " + user.getPassword());

        // 使用MD5验证密码
        String encryptedPassword = DigestUtils.md5DigestAsHex(loginParam.getPassword().getBytes());
        System.out.println("Encrypted password: " + encryptedPassword);
        
        if (!user.getPassword().equals(encryptedPassword)) {
            throw new RuntimeException("用户名或密码错误");
        }

        // 校验用户状态
        if (!"active".equals(user.getStatus())) {
            throw new RuntimeException("账号已被禁用");
        }

        // 生成token
        return jwtUtil.generateToken(user.getId());
    }

    @Override
    public Map<String, Object> getUserInfo() {
        // 从当前线程获取用户ID
        Long userId = JwtUtil.getCurrentUserId();
        User user = this.getById(userId);
        
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("id", user.getId());
        userInfo.put("username", user.getUsername());
        userInfo.put("name", user.getName());
        userInfo.put("phone", user.getPhone());
        userInfo.put("email", user.getEmail());
        userInfo.put("role", user.getRole());
        userInfo.put("status", user.getStatus());
        
        return userInfo;
    }

    @Override
    public void logout() {
        // 清除当前线程中的用户信息
        JwtUtil.clearCurrentUserId();
    }

    @Override
    public void resetAdminPassword() {
        // 查找管理员用户
        User admin = this.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, "admin"));
        
        if (admin != null) {
            // 使用MD5加密新密码
            String newPassword = DigestUtils.md5DigestAsHex("admin123".getBytes());
            
            // 更新密码
            admin.setPassword(newPassword);
            this.updateById(admin);
            
            System.out.println("管理员密码已重置为: admin123");
        }
    }
} 