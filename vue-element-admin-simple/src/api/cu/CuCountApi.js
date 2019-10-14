import request from '@/utils/request'

export function findByListCuCount(data) {
  return request({
    url: '/api-cu/cu/cucount/list',
    method: 'post',
    data
  })
}

export function findByIdCuCount(data) {
  return request({
    url: '/api-cu/cu/cucount/info',
    method: 'post',
    data
  })
}
export function createCuCount(data) {
  return request({
    url: '/api-cu/cu/cucount/save',
    method: 'post',
    data
  })
}

export function updateCuCount(data) {
  return request({
    url: '/api-cu/cu/cucount/update',
    method: 'post',
    data
  })
}

export function delCuCount(data) {
  return request({
    url: '/api-cu/cu/cucount/delete',
    method: 'post',
    data
  })
}

export function pushCuCount(data) {
  return request({
    url: '/api-cu/cu/cucount/push',
    method: 'post',
    data
  })
}

