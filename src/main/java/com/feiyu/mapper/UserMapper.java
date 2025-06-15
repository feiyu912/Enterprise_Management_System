package com.feiyu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feiyu.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user WHERE role = 'salesman' AND deleted = 0")
    List<User> selectSalesStaff();
} 