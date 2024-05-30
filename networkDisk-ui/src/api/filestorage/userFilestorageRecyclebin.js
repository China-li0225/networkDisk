import request from '@/utils/request'

// 查询用户文件存储列表
export function listUserFilestorageRecyclebin(query) {
  return request({
    url: '/filestorage/userFilestorageRecyclebin/list',
    method: 'get',
    params: query
  })
}

// 查询用户文件存储详细
export function getUserFilestorageRecyclebin(expirationId) {
  return request({
    url: '/filestorage/userFilestorageRecyclebin/' + expirationId,
    method: 'get'
  })
}

// 新增用户文件存储
export function addUserFilestorageRecyclebin(data) {
  return request({
    url: '/filestorage/userFilestorageRecyclebin',
    method: 'post',
    data: data
  })
}

// 修改用户文件存储
export function updateUserFilestorageRecyclebin(data) {
  return request({
    url: '/filestorage/userFilestorageRecyclebin',
    method: 'put',
    data: data
  })
}

// 删除用户文件存储
export function delUserFilestorageRecyclebin(expirationId) {
  return request({
    url: '/filestorage/userFilestorageRecyclebin/' + expirationId,
    method: 'delete'
  })
}
