package com.feiyu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.feiyu.entity.Product;
import com.feiyu.param.ProductQueryParam;

public interface ProductService extends IService<Product> {
    Page<Product> getProductPage(ProductQueryParam param);
} 