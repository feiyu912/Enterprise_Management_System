package com.feiyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feiyu.entity.Product;
import com.feiyu.mapper.ProductMapper;
import com.feiyu.param.ProductQueryParam;
import com.feiyu.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Override
    public Page<Product> getProductPage(ProductQueryParam param) {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        
        // 添加查询条件
        if (StringUtils.hasText(param.getProductName())) {
            wrapper.like(Product::getName, param.getProductName());
        }
        
        // 按创建时间降序排序
        wrapper.orderByDesc(Product::getCreateTime);
        
        // 执行分页查询
        return this.page(new Page<>(param.getPageNum(), param.getPageSize()), wrapper);
    }
} 