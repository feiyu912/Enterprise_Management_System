package com.feiyu.param;

import lombok.Data;

@Data
public class UserQueryParam {
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private String username;
} 