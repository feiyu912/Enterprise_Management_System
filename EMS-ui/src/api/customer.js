import request from '@/utils/request'

// 获取客户列表
export function getCustomerList(params) {
  return request({
    url: '/customer/list',
    method: 'get',
    params
  })
}

// 获取责任区客户列表
export function getSupervisorCustomers() {
  return request({
    url: '/customer/supervisor',
    method: 'get'
  })
}

// 获取销售人员列表
export function getSalesStaffList() {
  return request({
    url: '/user/sales',
    method: 'get'
  })
}

// 指派客户
export function assignCustomer(data) {
  return request({
    url: '/customer/assign',
    method: 'post',
    data
  })
}

// 添加客户
export function addCustomer(data) {
  return request({
    url: '/customer/add',
    method: 'post',
    data
  })
}

// 更新客户
export function updateCustomer(data) {
  return request({
    url: '/customer/update',
    method: 'put',
    data
  })
}

// 删除客户
export function deleteCustomer(id) {
  return request({
    url: `/customer/delete/${id}`,
    method: 'delete'
  })
} 