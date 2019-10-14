import request from '@/utils/request'

export function findByListSysRoleAuthorities(data) {
  return request({
    url: '/api-sys/sys/sysroleauthorities/list',
    method: 'post',
    data
  })
}
export function findByRightListSysRoleAuthorities(data) {
  return request({
    url: '/api-sys/sys/sysroleauthorities/rightList',
    method: 'post',
    data
  })
}
export function findByLeftListSysRoleAuthorities(data) {
  return request({
    url: '/api-sys/sys/sysroleauthorities/leftList',
    method: 'post',
    data
  })
}

export function findByIdSysRoleAuthorities(data) {
  return request({
    url: '/api-sys/sys/sysroleauthorities/info',
    method: 'post',
    data
  })
}
export function createSysRoleAuthorities(data) {
  return request({
    url: '/api-sys/sys/sysroleauthorities/save',
    method: 'post',
    data
  })
}

export function updateSysRoleAuthorities(data) {
  return request({
    url: '/api-sys/sys/sysroleauthorities/update',
    method: 'post',
    data
  })
}

export function delSysRoleAuthorities(data) {
  return request({
    url: '/api-sys/sys/sysroleauthorities/delete',
    method: 'post',
    data
  })
}

export function pushSysRoleAuthorities(data) {
  return request({
    url: '/api-sys/sys/sysroleauthorities/push',
    method: 'post',
    data
  })
}

