import request from '@/utils/request'

export function findByListCuSign(data) {
  return request({
    url: '/api-cu/cu/cusign/list',
    method: 'post',
    data
  })
}

export function findByIdCuSign(data) {
  return request({
    url: '/api-cu/cu/cusign/info',
    method: 'post',
    data
  })
}
export function createCuSign(data) {
  return request({
    url: '/api-cu/cu/cusign/save',
    method: 'post',
    data
  })
}

export function updateCuSign(data) {
  return request({
    url: '/api-cu/cu/cusign/update',
    method: 'post',
    data
  })
}

export function delCuSign(data) {
  return request({
    url: '/api-cu/cu/cusign/delete',
    method: 'post',
    data
  })
}

export function pushCuSign(data) {
  return request({
    url: '/api-cu/cu/cusign/push',
    method: 'post',
    data
  })
}

