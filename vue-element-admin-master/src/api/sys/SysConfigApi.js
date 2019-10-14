import request from '@/utils/request'

export function findByListSysConfig(data) {
  return request({
    url: '/api-sys/sys/sysconfig/list',
    method: 'post',
    data
  })
}

export function findByIdSysConfig(data) {
  return request({
    url: '/api-sys/sys/sysconfig/info',
    method: 'post',
    data
  })
}
export function findByNameSysConfig(data) {
  return request({
    url: '/api-sys/sys/sysconfig/findNameInfo',
    method: 'post',
    data
  })
}

export function createSysConfig(data) {
  return request({
    url: '/api-sys/sys/sysconfig/save',
    method: 'post',
    data
  })
}

export function updateSysConfig(data) {
  return request({
    url: '/api-sys/sys/sysconfig/update',
    method: 'post',
    data
  })
}

export function delSysConfig(data) {
  return request({
    url: '/api-sys/sys/sysconfig/delete',
    method: 'post',
    data
  })
}

export function pushSysConfig(data) {
  return request({
    url: '/api-sys/sys/sysconfig/push',
    method: 'post',
    data
  })
}

