import request from '@/utils/request'

export function findByListSysAuthoritiesResources(data) {
  return request({
    url: '/api-sys/sys/sysauthoritiesresources/list',
    method: 'post',
    data
  })
}
export function findByLeftListSysAuthoritiesResources(data) {
  return request({
    url: '/api-sys/sys/sysauthoritiesresources/leftList',
    method: 'post',
    data
  })
}
export function findByRightListSysAuthoritiesResources(data) {
  return request({
    url: '/api-sys/sys/sysauthoritiesresources/rightList',
    method: 'post',
    data
  })
}
export function findByIdSysAuthoritiesResources(data) {
  return request({
    url: '/api-sys/sys/sysauthoritiesresources/info',
    method: 'post',
    data
  })
}
export function createSysAuthoritiesResources(data) {
  return request({
    url: '/api-sys/sys/sysauthoritiesresources/save',
    method: 'post',
    data
  })
}

export function updateSysAuthoritiesResources(data) {
  return request({
    url: '/api-sys/sys/sysauthoritiesresources/update',
    method: 'post',
    data
  })
}

export function delSysAuthoritiesResources(data) {
  return request({
    url: '/api-sys/sys/sysauthoritiesresources/delete',
    method: 'post',
    data
  })
}

export function pushSysAuthoritiesResources(data) {
  return request({
    url: '/api-sys/sys/sysauthoritiesresources/push',
    method: 'post',
    data
  })
}

