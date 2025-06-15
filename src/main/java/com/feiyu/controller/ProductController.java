package com.feiyu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feiyu.entity.Product;
import com.feiyu.param.ProductQueryParam;
import com.feiyu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public Page<Product> getProductList(ProductQueryParam param) {
        log.info("查询产品列表，参数：{}", param);
        Page<Product> page = productService.getProductPage(param);
        log.info("查询结果：{}", page);
        return page;
    }

    @GetMapping("/page")
    public Page<Product> getProductPage(ProductQueryParam param) {
        log.info("分页查询产品列表，参数：{}", param);
        Page<Product> page = productService.getProductPage(param);
        log.info("查询结果：{}", page);
        return page;
    }

    @PostMapping("/add")
    public boolean addProduct(@RequestBody Product product) {
        log.info("添加产品，参数：{}", product);
        return productService.save(product);
    }

    @PutMapping("/update")
    public boolean updateProduct(@RequestBody Product product) {
        log.info("更新产品，参数：{}", product);
        return productService.updateById(product);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteProduct(@PathVariable Long id) {
        log.info("删除产品，ID：{}", id);
        return productService.removeById(id);
    }
} 