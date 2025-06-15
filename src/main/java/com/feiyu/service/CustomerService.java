package com.feiyu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.feiyu.entity.Customer;

import java.util.List;

public interface CustomerService extends IService<Customer> {
    Page<Customer> getCustomerPage(Integer pageNum, Integer pageSize, String customerName);
    List<Customer> getSupervisorCustomers(Long supervisorId);
    List<Customer> getSalesmanCustomers(Long salesmanId);
    void assignCustomers(List<Long> customerIds, Long salesmanId);
} 