import request from '@/utils/request'

export function findByListSysUserRole(data) {
  return request({
    url: '/api-sys/sys/sysuserrole/list',
    method: 'post',
    data
  })
}
export function findByLeftListSysUserRole(data) {
  return request({
    url: '/api-sys/sys/sysuserrole/leftList',
    method: 'post',
    data
  })
}
export function findByRightListSysUserRole(data) {
  return request({
    url: '/api-sys/sys/sysuserrole/rightList',
    method: 'post',
    data
  })
}

export function findByIdSysUserRole(data) {
  return request({
    url: '/api-sys/sys/sysuserrole/info',
    method: 'post',
    data
  })
}
export function createSysUserRole(data) {
  return request({
    url: '/api-sys/sys/sysuserrole/save',
    method: 'post',
    data
  })
}

export function updateSysUserRole(data) {
  return request({
    url: '/api-sys/sys/sysuserrole/update',
    method: 'post',
    data
  })
}

export function delSysUserRole(data) {
  return request({
    url: '/api-sys/sys/sysuserrole/delete',
    method: 'post',
    data
  })
}

export function pushSysUserRole(data) {
  return request({
    url: '/api-sys/sys/sysuserrole/push',
    method: 'post',
    data
  })
}

