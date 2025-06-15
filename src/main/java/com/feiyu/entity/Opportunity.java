package com.feiyu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName("opportunity")
public class Opportunity {
    @TableId(type = IdType.AUTO)
    private Long id;
    
    private String title;
    
    private Long customerId;
    
    private BigDecimal amount;
    
    private LocalDate expectedDate;
    
    private String stage;
    
    private Integer probability;
    
    private String description;
    
    private Long userId;
    
    private LocalDateTime createTime;
    
    private LocalDateTime updateTime;
} 