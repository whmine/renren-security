import request from '@/utils/request'

export function findByListSysDictData(data) {
  return request({
    url: '/api-sys/sys/sysdictdata/list',
    method: 'post',
    data
  })
}

export function findByIdSysDictData(data) {
  return request({
    url: '/api-sys/sys/sysdictdata/info',
    method: 'post',
    data
  })
}
export function createSysDictData(data) {
  return request({
    url: '/api-sys/sys/sysdictdata/save',
    method: 'post',
    data
  })
}

export function updateSysDictData(data) {
  return request({
    url: '/api-sys/sys/sysdictdata/update',
    method: 'post',
    data
  })
}

export function delSysDictData(data) {
  return request({
    url: '/api-sys/sys/sysdictdata/delete',
    method: 'post',
    data
  })
}

export function pushSysDictData(data) {
  return request({
    url: '/api-sys/sys/sysdictdata/push',
    method: 'post',
    data
  })
}

