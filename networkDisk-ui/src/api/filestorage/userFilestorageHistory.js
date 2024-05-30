import request from '@/utils/request'

// 查询用户文件历史记录列表
export function listUserFilestorageHistory(query) {
  return request({
    url: '/filestorage/userFilestorageHistory/list',
    method: 'get',
    params: query
  })
}

// 查询用户文件历史记录详细
export function getUserFilestorageHistory(historyId) {
  return request({
    url: '/filestorage/userFilestorageHistory/' + historyId,
    method: 'get'
  })
}

// 新增用户文件历史记录
export function addUserFilestorageHistory(data) {
  return request({
    url: '/filestorage/userFilestorageHistory',
    method: 'post',
    data: data
  })
}

// 修改用户文件历史记录
export function updateUserFilestorageHistory(data) {
  return request({
    url: '/filestorage/userFilestorageHistory',
    method: 'put',
    data: data
  })
}

// 删除用户文件历史记录
export function delUserFilestorageHistory(historyId) {
  return request({
    url: '/filestorage/userFilestorageHistory/' + historyId,
    method: 'delete'
  })
}
