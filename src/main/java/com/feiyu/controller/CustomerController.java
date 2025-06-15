package com.feiyu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feiyu.common.Result;
import com.feiyu.entity.Customer;
import com.feiyu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public Result<Page<Customer>> getCustomerPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String customerName) {
        return Result.success(customerService.getCustomerPage(pageNum, pageSize, customerName));
    }

    @GetMapping("/supervisor")
    public Result<List<Customer>> getSupervisorCustomers() {
        // 从当前登录用户中获取主管ID
        Long supervisorId = 1L; // 这里需要替换为实际的获取方式
        return Result.success(customerService.getSupervisorCustomers(supervisorId));
    }

    @GetMapping("/salesman/{salesmanId}")
    public Result<List<Customer>> getSalesmanCustomers(@PathVariable Long salesmanId) {
        return Result.success(customerService.getSalesmanCustomers(salesmanId));
    }

    @PostMapping("/assign")
    public Result<Void> assignCustomers(@RequestBody CustomerAssignDTO dto) {
        customerService.assignCustomers(dto.getCustomerIds(), dto.getSalesmanId());
        return Result.success();
    }

    @PostMapping("/add")
    public Result<Void> addCustomer(@RequestBody Customer customer) {
        customerService.save(customer);
        return Result.success();
    }

    @PutMapping("/update")
    public Result<Void> updateCustomer(@RequestBody Customer customer) {
        customerService.updateById(customer);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result<Void> deleteCustomer(@PathVariable Long id) {
        customerService.removeById(id);
        return Result.success();
    }
}

class CustomerAssignDTO {
    private List<Long> customerIds;
    private Long salesmanId;

    public List<Long> getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(List<Long> customerIds) {
        this.customerIds = customerIds;
    }

    public Long getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Long salesmanId) {
        this.salesmanId = salesmanId;
    }
} 