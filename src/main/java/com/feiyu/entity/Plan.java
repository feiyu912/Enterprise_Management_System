package com.feiyu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("plan")
public class Plan {
    @TableId(type = IdType.AUTO)
    private Long id;
    
    private String title;
    
    private String content;
    
    private LocalDateTime planTime;
    
    private String status;
    
    private Long customerId;
    
    private Long userId;
    
    private LocalDateTime createTime;
    
    private LocalDateTime updateTime;
} 