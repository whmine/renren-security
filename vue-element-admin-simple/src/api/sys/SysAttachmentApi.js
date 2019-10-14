import request from '@/utils/request'

export function findByListSysAttachment(data) {
  return request({
    url: '/api-sys/sys/sysattachment/list',
    method: 'post',
    data
  })
}

export function findByIdSysAttachment(data) {
  return request({
    url: '/api-sys/sys/sysattachment/info',
    method: 'post',
    data
  })
}
export function createSysAttachment(data) {
  return request({
    url: '/api-sys/sys/sysattachment/save',
    method: 'post',
    data
  })
}

export function updateSysAttachment(data) {
  return request({
    url: '/api-sys/sys/sysattachment/update',
    method: 'post',
    data
  })
}

export function delSysAttachment(data) {
  return request({
    url: '/api-sys/sys/sysattachment/delete',
    method: 'post',
    data
  })
}

export function pushSysAttachment(data) {
  return request({
    url: '/api-sys/sys/sysattachment/push',
    method: 'post',
    data
  })
}

