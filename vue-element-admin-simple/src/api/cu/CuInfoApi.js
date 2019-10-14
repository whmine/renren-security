import request from '@/utils/request'

export function findByListCuInfo(data) {
  return request({
    url: '/api-cu/cu/cuinfo/list',
    method: 'post',
    data
  })
}

export function findByIdCuInfo(data) {
  return request({
    url: '/api-cu/cu/cuinfo/info',
    method: 'post',
    data
  })
}
export function createCuInfo(data) {
  return request({
    url: '/api-cu/cu/cuinfo/save',
    method: 'post',
    data
  })
}

export function updateCuInfo(data) {
  return request({
    url: '/api-cu/cu/cuinfo/update',
    method: 'post',
    data
  })
}

export function delCuInfo(data) {
  return request({
    url: '/api-cu/cu/cuinfo/delete',
    method: 'post',
    data
  })
}

export function pushCuInfo(data) {
  return request({
    url: '/api-cu/cu/cuinfo/push',
    method: 'post',
    data
  })
}

