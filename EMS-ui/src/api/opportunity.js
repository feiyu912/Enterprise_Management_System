import request from '@/utils/request'

// 搜索商机
export function searchOpportunities(keyword) {
  return request({
    url: '/api/opportunities/search',
    method: 'get',
    params: { keyword }
  })
}

// 获取商机列表
export function getOpportunityList(params) {
  return request({
    url: '/opportunity/list',
    method: 'get',
    params
  })
}

// 添加商机
export function addOpportunity(data) {
  return request({
    url: '/opportunity/add',
    method: 'post',
    data
  })
}

// 更新商机
export function updateOpportunity(data) {
  return request({
    url: '/opportunity/update',
    method: 'put',
    data
  })
}

// 删除商机
export function deleteOpportunity(id) {
  return request({
    url: `/opportunity/delete/${id}`,
    method: 'delete'
  })
} 