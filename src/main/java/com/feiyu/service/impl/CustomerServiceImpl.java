package com.feiyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feiyu.entity.Customer;
import com.feiyu.mapper.CustomerMapper;
import com.feiyu.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Override
    public Page<Customer> getCustomerPage(Integer pageNum, Integer pageSize, String customerName) {
        LambdaQueryWrapper<Customer> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(customerName)) {
            wrapper.like(Customer::getCustomerName, customerName);
        }
        return page(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public List<Customer> getSupervisorCustomers(Long supervisorId) {
        LambdaQueryWrapper<Customer> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Customer::getSalesmanId, supervisorId);
        return list(wrapper);
    }

    @Override
    public List<Customer> getSalesmanCustomers(Long salesmanId) {
        LambdaQueryWrapper<Customer> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Customer::getSalesmanId, salesmanId);
        return list(wrapper);
    }

    @Override
    @Transactional
    public void assignCustomers(List<Long> customerIds, Long salesmanId) {
        for (Long customerId : customerIds) {
            Customer customer = getById(customerId);
            if (customer != null) {
                customer.setSalesmanId(salesmanId);
                updateById(customer);
            }
        }
    }
} 