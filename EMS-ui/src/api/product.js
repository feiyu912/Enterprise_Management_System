import request from '@/utils/request'

// 获取产品列表
export function getProductList(params) {
  return request({
    url: '/product/page',
    method: 'get',
    params
  })
}

// 添加产品
export function addProduct(data) {
  return request({
    url: '/product/add',
    method: 'post',
    data
  })
}

// 更新产品
export function updateProduct(data) {
  return request({
    url: '/product/update',
    method: 'put',
    data
  })
}

// 删除产品
export function deleteProduct(id) {
  return request({
    url: `/product/delete/${id}`,
    method: 'delete'
  })
} 