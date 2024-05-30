import request from '@/utils/request'

// 查询用户组信息列表
export function listGroup(query) {
  return request({
    url: '/system/group/list',
    method: 'get',
    params: query
  })
}

// 查询用户组信息详细
export function getGroup(groupId) {
  return request({
    url: '/system/group/' + groupId,
    method: 'get'
  })
}

// 新增用户组信息
export function addGroup(data) {
  return request({
    url: '/system/group',
    method: 'post',
    data: data
  })
}

// 修改用户组信息
export function updateGroup(data) {
  return request({
    url: '/system/group',
    method: 'put',
    data: data
  })
}

// 删除用户组信息
export function delGroup(groupId) {
  return request({
    url: '/system/group/' + groupId,
    method: 'delete'
  })
}
// 修改用户组状态
export function changeUserGroupStatus(groupId ,status) {
  const data = {
    groupId,
    status
  }
  return request({
    url: '/system/group/changeStatus',
    method: 'put',
    data: data
  })
}
