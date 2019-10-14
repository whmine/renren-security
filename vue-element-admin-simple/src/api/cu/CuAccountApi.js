import request from '@/utils/request'

export function findByListCuAccount(data) {
  return request({
    url: '/api-cu/cu/cuaccount/list',
    method: 'post',
    data
  })
}

export function findByIdCuAccount(data) {
  return request({
    url: '/api-cu/cu/cuaccount/info',
    method: 'post',
    data
  })
}
export function createCuAccount(data) {
  return request({
    url: '/api-cu/cu/cuaccount/save',
    method: 'post',
    data
  })
}

export function updateCuAccount(data) {
  return request({
    url: '/api-cu/cu/cuaccount/update',
    method: 'post',
    data
  })
}

export function delCuAccount(data) {
  return request({
    url: '/api-cu/cu/cuaccount/delete',
    method: 'post',
    data
  })
}

export function pushCuAccount(data) {
  return request({
    url: '/api-cu/cu/cuaccount/push',
    method: 'post',
    data
  })
}

