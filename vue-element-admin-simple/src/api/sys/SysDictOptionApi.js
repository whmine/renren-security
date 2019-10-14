import request from '@/utils/request'

export function findByListSysDictOption(data) {
  return request({
    url: '/api-sys/sys/sysdictoption/list',
    method: 'post',
    data
  })
}
export function findByTypeListSysDictOption(data) {
  return request({
    url: '/api-sys/sys/sysdictoption/typeList',
    method: 'post',
    data
  })
}

export function findByIdSysDictOption(data) {
  return request({
    url: '/api-sys/sys/sysdictoption/info',
    method: 'post',
    data
  })
}
export function createSysDictOption(data) {
  return request({
    url: '/api-sys/sys/sysdictoption/save',
    method: 'post',
    data
  })
}

export function updateSysDictOption(data) {
  return request({
    url: '/api-sys/sys/sysdictoption/update',
    method: 'post',
    data
  })
}

export function delSysDictOption(data) {
  return request({
    url: '/api-sys/sys/sysdictoption/delete',
    method: 'post',
    data
  })
}

export function pushSysDictOption(data) {
  return request({
    url: '/api-sys/sys/sysdictoption/push',
    method: 'post',
    data
  })
}

