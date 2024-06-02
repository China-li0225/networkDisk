import request from '@/utils/request'

// 查询用户文件存储列表
export function listFilestorage(query) {
  return request({
    url: '/filestorage/list',
    method: 'get',
    params: query
  })
}

// 查询用户文件存储详细
export function getFilestorage(filestorageId) {
  return request({
    url: '/filestorage/' + filestorageId,
    method: 'get'
  })
}

// 新增用户文件存储
export function addFilestorage(data) {
  console.log(data)
  //提交数据
  return request({
    url: '/filestorage/uploads',
    method: 'post',
    data: data
  })
}

// 修改用户文件存储
export function updateFilestorage(data) {
  return request({
    url: '/filestorage',
    method: 'put',
    data: data
  })
}

// 删除用户文件存储
export function delFilestorage(filestorageId) {
  return request({
    url: '/filestorage/' + filestorageId,
    method: 'delete'
  })
}
