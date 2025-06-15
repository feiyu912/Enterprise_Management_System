package com.feiyu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_region")
public class Region {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String code;
    private String name;
    private Long parentId;
    private Integer level;
} 