import request from '@/utils/request'

// 获取角色列表
export function getRoleList(params) {
  return request({
    url: '/role/list',
    method: 'get',
    params
  })
}

// 添加角色
export function addRole(data) {
  return request({
    url: '/role/add',
    method: 'post',
    data
  })
}

// 更新角色
export function updateRole(data) {
  return request({
    url: '/role/update',
    method: 'put',
    data
  })
}

// 删除角色
export function deleteRole(id) {
  return request({
    url: `/role/delete/${id}`,
    method: 'delete'
  })
} 