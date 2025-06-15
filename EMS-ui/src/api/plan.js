import request from '@/utils/request'

// 获取计划列表
export function getPlanList(params) {
  return request({
    url: '/plan/list',
    method: 'get',
    params
  })
}

// 添加计划
export function addPlan(data) {
  return request({
    url: '/plan/add',
    method: 'post',
    data
  })
}

// 更新计划
export function updatePlan(data) {
  return request({
    url: '/plan/update',
    method: 'put',
    data
  })
}

// 删除计划
export function deletePlan(id) {
  return request({
    url: `/plan/delete/${id}`,
    method: 'delete'
  })
} 