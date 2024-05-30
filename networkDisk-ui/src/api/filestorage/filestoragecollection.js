import request from '@/utils/request'

// 查询用户收藏的文件列表
export function listFilestoragecollection(query) {
  return request({
    url: '/filestorage/filestoragecollection/list',
    method: 'get',
    params: query
  })
}

// 查询用户收藏的文件详细
export function getFilestoragecollection(collectionId) {
  return request({
    url: '/filestorage/filestoragecollection/' + collectionId,
    method: 'get'
  })
}

// 新增用户收藏的文件
export function addFilestoragecollection(data) {
  return request({
    url: '/filestorage/filestoragecollection',
    method: 'post',
    data: data
  })
}

// 修改用户收藏的文件
export function updateFilestoragecollection(data) {
  return request({
    url: '/filestorage/filestoragecollection',
    method: 'put',
    data: data
  })
}

// 删除用户收藏的文件
export function delFilestoragecollection(collectionId) {
  return request({
    url: '/filestorage/filestoragecollection/' + collectionId,
    method: 'delete'
  })
}
