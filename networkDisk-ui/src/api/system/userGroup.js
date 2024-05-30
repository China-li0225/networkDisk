import request from '@/utils/request'

// 查询用户与用户组关联列表
export function listUserGroup(query) {
  return request({
    url: '/system/userGroup/list',
    method: 'get',
    params: query
  })
}

// 查询用户与用户组关联详细
export function getUserGroup(userId) {
  return request({
    url: '/system/userGroup/' + userId,
    method: 'get'
  })
}

// 新增用户与用户组关联
export function addUserGroup(data) {
  return request({
    url: '/system/userGroup',
    method: 'post',
    data: data
  })
}

// 修改用户与用户组关联
export function updateUserGroup(data) {
  return request({
    url: '/system/userGroup',
    method: 'put',
    data: data
  })
}

// 删除用户与用户组关联
export function delUserGroup(userId) {
  return request({
    url: '/system/userGroup/' + userId,
    method: 'delete'
  })
}
