package com.feiyu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("user")
public class User {
    
    @TableId(type = IdType.AUTO)
    private Long id;
    
    private String username;
    
    private String password;
    
    private String name;
    
    private String phone;
    
    private String email;
    
    private String role;
    
    private String status;
    
    @TableLogic
    private Integer deleted;
    
    private LocalDateTime createTime;
    
    private LocalDateTime updateTime;
} 