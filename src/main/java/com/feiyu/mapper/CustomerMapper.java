package com.feiyu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feiyu.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {
} 