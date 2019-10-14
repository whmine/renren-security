import request from '@/utils/request'

export function findByListCuProfit(data) {
  return request({
    url: '/api-cu/cu/cuprofit/list',
    method: 'post',
    data
  })
}

export function findByIdCuProfit(data) {
  return request({
    url: '/api-cu/cu/cuprofit/info',
    method: 'post',
    data
  })
}
export function createCuProfit(data) {
  return request({
    url: '/api-cu/cu/cuprofit/save',
    method: 'post',
    data
  })
}

export function updateCuProfit(data) {
  return request({
    url: '/api-cu/cu/cuprofit/update',
    method: 'post',
    data
  })
}

export function delCuProfit(data) {
  return request({
    url: '/api-cu/cu/cuprofit/delete',
    method: 'post',
    data
  })
}

export function pushCuProfit(data) {
  return request({
    url: '/api-cu/cu/cuprofit/push',
    method: 'post',
    data
  })
}

