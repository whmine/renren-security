import request from '@/utils/request'

export function findByListSysUser(data) {
  return request({
    url: '/api-sys/sys/sysuser/list',
    method: 'post',
    data
  })
}

export function findByIdSysUser(data) {
  return request({
    url: '/api-sys/sys/sysuser/info',
    method: 'post',
    data
  })
}
export function createSysUser(data) {
  return request({
    url: '/api-sys/sys/sysuser/save',
    method: 'post',
    data
  })
}

export function updateSysUser(data) {
  return request({
    url: '/api-sys/sys/sysuser/update',
    method: 'post',
    data
  })
}

export function delSysUser(data) {
  return request({
    url: '/api-sys/sys/sysuser/delete',
    method: 'post',
    data
  })
}

export function pushSysUser(data) {
  return request({
    url: '/api-sys/sys/sysuser/push',
    method: 'post',
    data
  })
}

