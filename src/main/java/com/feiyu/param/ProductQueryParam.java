package com.feiyu.param;

import lombok.Data;

@Data
public class ProductQueryParam {
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private String productName;
} 