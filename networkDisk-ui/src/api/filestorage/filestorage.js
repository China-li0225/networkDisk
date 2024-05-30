import request from '@/utils/request'

// 查询用户文件存储列表
export function listFilestorage(query) {
  return request({
    url: '/filestorage/filestorage/list',
    method: 'get',
    params: query
  })
}

// 查询用户文件存储详细
export function getFilestorage(filestorageId) {
  return request({
    url: '/filestorage/filestorage/' + filestorageId,
    method: 'get'
  })
}

// 新增用户文件存储
export function addFilestorage(data) {
  return request({
    url: '/filestorage/filestorage',
    method: 'post',
    data: data
  })
}

// 修改用户文件存储
export function updateFilestorage(data) {
  return request({
    url: '/filestorage/filestorage',
    method: 'put',
    data: data
  })
}

// 删除用户文件存储
export function delFilestorage(filestorageId) {
  return request({
    url: '/filestorage/filestorage/' + filestorageId,
    method: 'delete'
  })
}
